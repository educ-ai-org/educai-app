package com.example.educai.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.educai.R
import com.example.educai.ui.theme.LightGrey
import com.example.educai.ui.theme.MediumPurple
import com.example.educai.ui.theme.montserratFontFamily
import com.example.educai.utils.toDate

@Composable
fun Post(
    title: String,
    date: String,
    description: String,
    fileName: String,
    fileUrl: String? = null
) {
    val context = LocalContext.current

    val borderShape = RoundedCornerShape(10.dp)
    val roundedJustOnTop = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
    val borderStroke = BorderStroke(1.dp, LightGrey)

    val fonteTitulo = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold, fontFamily = montserratFontFamily)
    val fonteDescricao = TextStyle(fontSize = 16.sp, color = Color.Gray, fontFamily = montserratFontFamily)
    val fonteLink = TextStyle(
        fontSize = 16.sp,
        color = MediumPurple,
        textDecoration = TextDecoration.Underline,
        fontFamily = montserratFontFamily
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .border(borderStroke, borderShape)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .border(borderStroke, roundedJustOnTop)
                    .padding(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .padding(
                                start = 10.dp
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.purple_book_icon),
                            contentDescription = "Ícone de livro",
                            modifier = Modifier.size(30.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = title,
                            style = fonteTitulo,
                            modifier = Modifier
                                .padding(
                                    top = 2.dp
                                )
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Data de publicação: ${date.toDate()}",
                    style = fonteDescricao
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = description,
                    style = fonteDescricao
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = fileName,
                    style = fonteLink,
                    modifier = Modifier.clickable {
                        fileUrl?.let { openDocumentInBrowser(context, it) }
                    }
                )
            }
        }
    }
}

fun openDocumentInBrowser(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}

@Composable
@Preview
fun PostPreview() {
    Post(
        title = "Título de Exemplo",
        date = "01/01/2024",
        fileName = "Documento de Exemplo.pdf",
        description = "Descrição do documento",
        fileUrl = "https://www.example.com/document.pdf"
    )
}
