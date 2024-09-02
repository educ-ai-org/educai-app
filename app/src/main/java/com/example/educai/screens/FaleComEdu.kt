package com.example.educai.screens

import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.educai.ui.theme.BackgroundColor
import com.example.educai.R
import com.example.educai.ui.theme.MediumPurple
import java.io.IOException
import java.time.LocalDateTime

@Composable
fun FaleComEdu() {
    val listState = rememberLazyListState()
    val context = LocalContext.current

    val recognizer = SpeechRecognizer.createSpeechRecognizer(context)

    val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
        putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
        putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US")
    }

    var messages by remember {
        mutableStateOf(
            listOf(
                Message("Hello How are you?", LocalDateTime.now(), MessageType.SEND),
                Message("I am fine! Thank you", LocalDateTime.now(), MessageType.RECEIVE)
            )
        )
    }

    recognizer.setRecognitionListener(object:RecognitionListener {
        override fun onReadyForSpeech(params: Bundle?) = Unit

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
                SpeechRecognizer.ERROR_NO_MATCH -> "No recognition result matched."
                SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "RecognitionService busy"
                SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "Insufficient permissions"
                else -> "Didn't understand, please try again."
            }

            Toast.makeText(context,"Error: $msgError", Toast.LENGTH_SHORT).show()
        }

        override fun onResults(results: Bundle?) {
            results
                ?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                ?.getOrNull(0)
                ?.let { text ->
                    messages = messages + Message(text = text, LocalDateTime.now(), MessageType.SEND)
                }
        }

        override fun onPartialResults(partialResults: Bundle?) = Unit

        override fun onEvent(eventType: Int, params: Bundle?) = Unit
    })

    var recordingAudio by remember {
        mutableStateOf(false)
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
            modifier = Modifier
                .fillMaxHeight(.9f)
        ) {
            items(messages) { item ->
                MessageComponent(item)
            }
        }

        RecordButton(
            recordingAudio = recordingAudio,
            onClick = {
                if(!recordingAudio) {
                    recognizer.startListening(intent)
                } else {
                    recognizer.stopListening()
                }

                recordingAudio = !recordingAudio
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
    SEND
}

data class Message(
    val text: String,
    val date: LocalDateTime,
    val type: MessageType
)

@Composable
fun MessageComponent(message: Message) {
    val isSend = message.type == MessageType.SEND;

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
                painter = painterResource(id =
                    if(isSend) R.drawable.profileimage else R.drawable.robot
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

                    append("10:00 am")
                },
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(horizontal = 6.dp)
            )
        }

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

@Composable
fun RecordButton(recordingAudio: Boolean, onClick: () -> Unit) {
    OutlinedButton(
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
