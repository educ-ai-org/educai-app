package com.example.educai.screens

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.educai.R
import com.example.educai.ui.theme.BackgroundColor
import com.example.educai.ui.theme.MediumPurple
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.time.temporal.ChronoUnit
import java.util.Locale
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.educai.data.model.EduRequest
import com.example.educai.data.viewmodel.IAViewModel
import com.example.educai.data.model.Message
import com.example.educai.data.model.MessageType

@SuppressLint("RememberReturnType")
@Composable
fun FaleComEdu(viewModel: IAViewModel = viewModel()) {
    val listState = rememberLazyListState()
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val recognizer = remember { SpeechRecognizer.createSpeechRecognizer(context) }

    val intent = remember {
        Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US")
        }
    }

    var recordingAudio by remember { mutableStateOf(false) }

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            if (!recordingAudio) {
                recognizer.startListening(intent)
            } else {
                recognizer.stopListening()
            }
        } else {
            Toast.makeText(context, "Permissão de áudio necessária", Toast.LENGTH_SHORT).show()
        }
    }

    recognizer.setRecognitionListener(object : RecognitionListener {
        override fun onReadyForSpeech(params: Bundle?) {
            recordingAudio = true
        }

        override fun onBeginningOfSpeech() = Unit
        override fun onRmsChanged(rmsdB: Float) = Unit
        override fun onBufferReceived(buffer: ByteArray?) = Unit
        override fun onEndOfSpeech() = Unit

        override fun onError(error: Int) {
            val msgError = when (error) {
                SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "Network timeout"
                SpeechRecognizer.ERROR_NETWORK -> "Network error"
                SpeechRecognizer.ERROR_AUDIO -> "Audio recording error"
                SpeechRecognizer.ERROR_SERVER -> "Server error"
                SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "Speech input"
                SpeechRecognizer.ERROR_NO_MATCH -> "Não consegui entender, pode repitir por favo?"
                SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "RecognitionService busy"
                SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "Insufficient permissions"
                else -> "Didn't understand, please try again."
            }

            recordingAudio = false
            Toast.makeText(context, "Error: $msgError", Toast.LENGTH_SHORT).show()
        }

        override fun onResults(results: Bundle?) {
            results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                ?.getOrNull(0)?.let { text ->
                    val formatedText = text.replaceFirstChar(Char::titlecaseChar)
                    viewModel.removeLastMessage()
                    viewModel.addMessage(
                        Message(
                            text = formatedText,
                            date = LocalDateTime.now(),
                            type = MessageType.SEND
                        )
                    )
                    recordingAudio = false

                    coroutineScope.launch {
                        viewModel.addMessage(
                            Message(
                                text = "",
                                date = LocalDateTime.now(),
                                type = MessageType.WRITING_RECEIVE
                            )
                        )

                        viewModel.getEduResponse(EduRequest(question = text))
                    }
                }
        }

        override fun onPartialResults(partialResults: Bundle?) = Unit
        override fun onEvent(eventType: Int, params: Bundle?) = Unit
    })

    LaunchedEffect(viewModel.messages.value?.size) {
        viewModel.messages.value?.size?.let { listState.animateScrollToItem(it) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(24.dp)
    ) {
        EduProfile()

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxHeight(0.9f)
        ) {
            viewModel.messages.value?.let {
                items(it.toList()) { item ->
                    MessageComponent(item)
                }
            }
        }

        RecordButton(
            recordingAudio = recordingAudio,
            recordButtonIsDisabled = viewModel.eduHasSpeaking,
            onClick = {
                if (ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.RECORD_AUDIO
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    requestPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                } else {
                    if (!recordingAudio) {
                        viewModel.addMessage(
                            Message(
                                text = "",
                                date = LocalDateTime.now(),
                                type = MessageType.WRITING_SEND
                            )
                        )

                        viewModel.stopSpeak()
                        recognizer.startListening(intent)
                    } else {
                        recognizer.stopListening()
                    }
                }
            }
        )
    }
}

@Preview
@Composable
fun FaleComEduPreview() {
    FaleComEdu()
}

@Composable
fun EduProfile() {
    Row(
        modifier = Modifier
            .border(1.dp, Color(0xFFD4D4D4), shape = RoundedCornerShape(10.dp))
            .padding(
                horizontal = 24.dp,
                vertical = 16.dp
            )
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.robot),
            contentDescription = "Foto do Edu",
            modifier = Modifier
                .height(38.dp)
        )

        Column(
            modifier = Modifier
                .padding(
                    start = 16.dp
                ),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Chat Edu",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                OnlineStatusDot()
                Text(
                    text = "Online",
                    fontSize = 10.sp,
                    color = Color(0xFF00B115),
                    modifier = Modifier
                        .padding(
                            start = 4.dp
                        )
                )
            }
        }
    }
}

@Composable
fun OnlineStatusDot() {
    val scale by rememberInfiniteTransition(label = "").animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Box(
        modifier = Modifier
            .size(10.dp)
            .graphicsLayer(scaleX = scale, scaleY = scale)
            .border(
                .5.dp, Color(0xFF00B115),
                shape = RoundedCornerShape(50)
            )
            .background(Color.Transparent, shape = CircleShape)
    ) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .padding(2.dp)
                .background(Color(0xFF00B115), shape = CircleShape)
        )
    }
}

enum class MessageType {
    RECEIVE,
    SEND,
    WRITING_SEND,
    WRITING_RECEIVE
}

data class Message(
    val text: String,
    val date: LocalDateTime,
    val type: MessageType
)

@Composable
fun MessageComponent(message: Message) {
    val isSend = message.type == MessageType.SEND || message.type == MessageType.WRITING_SEND;

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 12.dp,
                bottom = 12.dp
            ),
        horizontalAlignment = if (isSend) Alignment.End else Alignment.Start
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(
                    id =
                    if (isSend) R.drawable.profileimage else R.drawable.robot
                ),
                contentDescription = "Foto do Edu",
                modifier = Modifier
                    .height(20.dp)
                    .clip(RoundedCornerShape(50.dp))
            )

            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append(text = if (isSend) "You" else "Edu")
                        append(" · ")
                    }

                    append(message.date.formatDateTime())
                },
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(horizontal = 6.dp)
            )
        }

        if (message.type == MessageType.WRITING_SEND || message.type == MessageType.WRITING_RECEIVE) {
            Box(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        top = 6.dp
                    )
                    .border(1.dp, Color(0xFF7750DE), RoundedCornerShape(20.dp))
                    .shadow(8.dp, RoundedCornerShape(20.dp))
                    .background(Color.White)
                    .padding(
                        horizontal = 42.dp,
                        vertical = 12.dp
                    )
            ) {
                TypingAnimation()
            }
        } else {
            Text(
                text = message.text,
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        top = 6.dp
                    )
                    .border(1.dp, Color(0xFF7750DE), RoundedCornerShape(20.dp))
                    .shadow(8.dp, RoundedCornerShape(20.dp))
                    .background(Color.White)
                    .padding(
                        horizontal = 42.dp,
                        vertical = 12.dp
                    )
            )
        }
    }
}

@Composable
fun RecordButton(recordingAudio: Boolean, recordButtonIsDisabled: Boolean, onClick: () -> Unit) {
    OutlinedButton(
        enabled = !recordButtonIsDisabled,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (recordingAudio) MediumPurple else Color.Transparent
        ),
        onClick = { onClick() }
    ) {
        if (recordingAudio)
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(2.dp))
                    .size(20.dp)
                    .background(Color.White)
            )
        else
            Image(
                painter = painterResource(id = R.drawable.icone_mic),
                contentDescription = "Icone microfone",
                modifier = Modifier
                    .height(20.dp)
            )
    }
}

@Composable
fun TypingAnimation() {
    val infiniteTransition = rememberInfiniteTransition()

    val dot1Size by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    val dot2Size by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(500, easing = FastOutSlowInEasing, delayMillis = 150),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    val dot3Size by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(500, easing = FastOutSlowInEasing, delayMillis = 300),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Row(
        modifier = Modifier
            .width(60.dp)
            .height(26.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Dot(size = dot1Size)
        Spacer(modifier = Modifier.width(8.dp))
        Dot(size = dot2Size)
        Spacer(modifier = Modifier.width(8.dp))
        Dot(size = dot3Size)
    }
}

@Composable
fun Dot(size: Float) {
    Box(
        modifier = Modifier
            .size((10 * size).dp)
            .background(color = MediumPurple, shape = CircleShape)
    )
}

fun LocalDateTime.formatDateTime(): String {
    val now = LocalDateTime.now()
    val daysDifference = ChronoUnit.DAYS.between(this.toLocalDate(), now.toLocalDate())

    return if (daysDifference < 7) {
        val dayOfWeek = this.dayOfWeek.getDisplayName(TextStyle.FULL, Locale("en", "US"))
        val time = this.format(DateTimeFormatter.ofPattern("HH:mm"))
        "$dayOfWeek, $time"
    } else {
        val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy, HH:mm")
        this.format(dateFormatter)
    }
}