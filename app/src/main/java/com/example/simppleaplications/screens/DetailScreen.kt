package com.example.simppleaplications.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.simppleaplications.vm.ItemViewModel
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(itemId: Long, onBack: () -> Unit, vm: ItemViewModel = viewModel()) {
    val scope = rememberCoroutineScope()
    var item by remember { mutableStateOf<com.example.simppleaplications.data.Item?>(null) }

    LaunchedEffect(itemId) {
        scope.launch {
            item = vm.getById(itemId)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(item?.title ?: "Detalhe") },
                navigationIcon = {
                    IconButton(onClick = onBack) { Text("Voltar") }
                }
            )
        }
    ) { padding ->
        item?.let { itItem ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                itItem.imageUri?.let { uri ->
                    AsyncImage(
                        model = uri,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                    )
                }
                Spacer(Modifier.height(12.dp))
                Text(itItem.title, style = MaterialTheme.typography.headlineSmall)
                Spacer(Modifier.height(8.dp))
                Text(itItem.description)
            }
        } ?: run {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Carregando...")
            }
        }
    }
}
