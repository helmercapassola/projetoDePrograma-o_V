package com.example.simppleaplications.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.simppleaplications.vm.ItemViewModel

@Composable
fun AddItemScreen(onSaved: () -> Unit, vm: ItemViewModel = viewModel()) {
    var title by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUri = uri
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Novo item", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(12.dp))
        TextField(value = title, onValueChange = { title = it }, label = { Text("Título") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))
        TextField(value = desc, onValueChange = { desc = it }, label = { Text("Descrição") }, modifier = Modifier.fillMaxWidth(), maxLines = 6)
        Spacer(Modifier.height(8.dp))
        Button(onClick = { launcher.launch("image/*") }) {
            Text("Selecionar imagem")
        }
        Spacer(Modifier.height(8.dp))
        imageUri?.let {
            AsyncImage(model = it, contentDescription = null, modifier = Modifier.height(160.dp).fillMaxWidth())
        }
        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            vm.insert(title, desc, imageUri?.toString())
            onSaved()
        }, enabled = title.isNotBlank()) {
            Text("Salvar")
        }
    }
}
