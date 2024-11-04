package com.example.educai.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.educai.R
import com.example.educai.data.viewmodel.MaterialViewModel
import com.example.educai.ui.theme.GrayBold
import com.example.educai.ui.theme.LightPurple

val fonteBold = FontFamily(
    Font(R.font.montserrat_bold, FontWeight.Normal)
)

val fonte = FontFamily(
    Font(R.font.montserrat_semibold, FontWeight.Normal)
)

data class CheckboxData(
    val title: String,
    val checkboxTitle: String,
    val height: Dp,
    val icon: Int,
    var checked: Boolean = false,
    var value: String = "",
)

@Composable
fun MaterialCreation(materialViewModel: MaterialViewModel = viewModel()) {
    val fonteBoldTitulo = MaterialTheme.typography.titleMedium.copy(fontFamily = fonteBold)
    val fonteMedia = MaterialTheme.typography.bodySmall.copy(fontFamily = fonte)
    val fonteSemiboldTitulo = MaterialTheme.typography.titleMedium.copy(fontFamily = fonte)

    var youtubeLinks by remember { mutableStateOf(listOf("", "", "", "")) }
    var instructions by remember { mutableStateOf("") }
    var youtubeLink by remember { mutableStateOf("") }
    var allCheckboxData by remember {
        mutableStateOf(
            listOf(
                CheckboxData("Escreva instruções", "Instruções", height = 60.dp, icon = R.drawable.instrucoes),
                CheckboxData("Link do youtube", "Link youtube", height = 60.dp, icon = R.drawable.link),
                CheckboxData("Carregar Documento", "Documento", height = 60.dp, icon = R.drawable.documento),
                CheckboxData("Carregar arquivo de áudio MP3", "MP3", height = 60.dp, icon = R.drawable.music),
            )
        )
    }

    val documentLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        uri?.let {
            allCheckboxData = allCheckboxData.map {
                if (it.title == "Carregar Documento") it.copy(value = uri.toString()) else it
            }
        }
    }

    val audioLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        uri?.let {
            allCheckboxData = allCheckboxData.map {
                if (it.title == "Carregar arquivo de áudio MP3") it.copy(value = uri.toString()) else it
            }
        }
    }

    val context = LocalContext.current
    val isLoading by materialViewModel.isLoading.observeAsState(false)
    val showErrorModal = remember { mutableStateOf(false) }

    fun clearAllFields() {
        allCheckboxData = allCheckboxData.map { it.copy(value = "", checked = false) }
    }

    fun clearFieldIfUnchecked(data: CheckboxData) {
        if (!data.checked) {
            allCheckboxData = allCheckboxData.map {
                if (it.title == data.title) it.copy(value = "") else it
            }
        }
    }

    LaunchedEffect(isLoading) {
        if (!isLoading) {
            if (materialViewModel.errorMessage.value != null) {
                showErrorModal.value = true
            } else {
                clearAllFields()
            }
        }
    }

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Text(
                text = "Criação de material",
                style = fonteBoldTitulo,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.edu_robot),
                    contentDescription = "Robo Edu",
                    modifier = Modifier.size(50.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .border(BorderStroke(1.dp, Color.Gray), shape = RoundedCornerShape(8.dp))
                        .padding(8.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "Quais tipos de entradas você fornecerá para a criação do material didático?",
                        style = fonteMedia,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
                    .height(100.dp)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    userScrollEnabled = false
                ) {
                    items(allCheckboxData.size) { index ->
                        CheckboxMaterial(
                            data = allCheckboxData[index],
                            onCheckedChange = { checked ->
                                allCheckboxData = allCheckboxData.toMutableList().apply {
                                    this[index] = this[index].copy(checked = checked)
                                }
                                clearFieldIfUnchecked(allCheckboxData[index])
                            }
                        )
                    }
                }
            }
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Card(
                    border = BorderStroke(1.dp, color = GrayBold),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.White)
                ) {
                    Column(
                        modifier = Modifier.padding(top = 24.dp, bottom = 32.dp, start = 32.dp, end = 32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Entradas",
                            style = fonteSemiboldTitulo,
                            textAlign = TextAlign.Center,
                        )

                        allCheckboxData.forEach { checkboxData ->
                            if (checkboxData.checked) {
                                Spacer(modifier = Modifier.height(8.dp))
                                when (checkboxData.title) {
                                    "Escreva instruções", "Link do youtube" -> {
                                        OutlinedTextField(
                                            value = checkboxData.value,
                                            onValueChange = { newValue ->
                                                allCheckboxData = allCheckboxData.map {
                                                    if (it.title == checkboxData.title) it.copy(value = newValue) else it
                                                }
                                            },
                                            label = {
                                                Row(
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    horizontalArrangement = Arrangement.Start,
                                                    modifier = Modifier.height(32.dp)
                                                ) {
                                                    Spacer(modifier = Modifier.width(8.dp))
                                                    LabelWithIcon(id = checkboxData.icon, texto = checkboxData.title)
                                                    Spacer(modifier = Modifier.width(8.dp))
                                                }
                                            },
                                            colors = OutlinedTextFieldDefaults.colors(
                                                unfocusedBorderColor = GrayBold
                                            ),
                                            textStyle = MaterialTheme.typography.bodySmall.copy(fontFamily = fonte),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(checkboxData.height)
                                        )
                                    }
                                    "Carregar arquivo de áudio MP3" -> {
                                        OutlinedButton(
                                            onClick = { audioLauncher.launch(arrayOf("audio/*")) },
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(checkboxData.height),
                                            border = BorderStroke(1.dp, GrayBold),
                                            shape = RoundedCornerShape(4.dp)
                                        ) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.Start,
                                                modifier = Modifier.fillMaxWidth()
                                            ) {
                                                LabelWithIcon(
                                                    id = checkboxData.icon,
                                                    texto = checkboxData.title,
                                                    textColor = if (checkboxData.value.isNotEmpty()) Color.Blue else GrayBold
                                                )
                                            }
                                        }
                                    }
                                    "Carregar Documento" -> {
                                        OutlinedButton(
                                            onClick = { documentLauncher.launch(arrayOf("application/pdf")) },
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(checkboxData.height),
                                            border = BorderStroke(1.dp, GrayBold),
                                            shape = RoundedCornerShape(4.dp)
                                        ) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.Start,
                                                modifier = Modifier.fillMaxWidth()
                                            ) {
                                                LabelWithIcon(
                                                    id = checkboxData.icon,
                                                    texto = checkboxData.title,
                                                    textColor = if (checkboxData.value.isNotEmpty()) Color.Blue else GrayBold
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 36.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Button(
                    onClick = {
                        materialViewModel.postMaterial(
                            context,
                            allCheckboxData.find { it.title == "Escreva instruções" }?.value,
                            allCheckboxData.find { it.title == "Link do youtube" }?.value,
                            allCheckboxData.find { it.title == "Carregar arquivo de áudio MP3" }?.value?.let { Uri.parse(it) },
                            allCheckboxData.find { it.title == "Carregar Documento" }?.value?.let { Uri.parse(it) }
                        )
                    },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .width(260.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = LightPurple)
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(color = Color.White)
                    } else {
                        Text("Gerar Material Didático")
                    }
                }
            }
        }
    }

    if (showErrorModal.value) {
        ErrorModal(onDismiss = { showErrorModal.value = false })
    }
}


@Composable
fun CheckboxMaterial(data: CheckboxData, onCheckedChange: (Boolean) -> Unit) {
    val fonteMedia = MaterialTheme.typography.bodySmall.copy(fontFamily = com.example.educai.screens.turma.fonte, fontWeight = FontWeight.Bold)

    Surface(
        modifier = Modifier
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(2.dp, Color(0xFF8B4AF4)),
        color = Color.White,
    ) {
        Row(
            modifier = Modifier
                .padding(start = 2.dp, end = 2.dp, top = 8.dp, bottom = 8.dp)
                .fillMaxWidth(0.5f)
                .height(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = data.checked,
                onCheckedChange = onCheckedChange,
                colors = CheckboxDefaults.colors(
                    checkmarkColor = Color(0xFF8B4AF4),
                    uncheckedColor = Color(0xFF8B4AF4),
                    checkedColor = Color.White,
                ),
                modifier = Modifier.scale(0.8f),
            )
            Text(
                text = data.checkboxTitle,
                style = fonteMedia,
            )
        }
    }
}

@Composable
fun LabelWithIcon(id: Int, texto: String, textColor: Color = Color.Black) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.height(20.dp)
    ) {
        Image(
            painter = painterResource(id = id),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = texto, fontSize = 12.sp, color = textColor)
    }
}

@Composable
fun ErrorModal(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Erro", style = MaterialTheme.typography.titleMedium)
        },
        text = {
            Text(text = "Não foi possível gerar material. Tente novamente.")
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("OK")
            }
        }
    )
}
