package com.example.simppleaplications.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.simppleaplications.vm.ItemViewModel

@Composable
fun HomeScreen(onAdd: () -> Unit, onOpenDetail: (Long) -> Unit, vm: ItemViewModel = viewModel()) {
    val items by vm.items.collectAsState()
    var expandedId by remember { mutableStateOf<Long?>(null) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAdd) { Text("+") }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding).fillMaxSize(), contentPadding = PaddingValues(16.dp)) {
            items(items) { item ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expandedId = if (expandedId == item.id) null else item.id }
                        .padding(12.dp)
                    ) {
                        Text(item.title, style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(6.dp))
                        if (item.imageUri != null) {
                            AsyncImage(model = item.imageUri, contentDescription = null, modifier = Modifier.height(160.dp).fillMaxWidth())
                            Spacer(Modifier.height(6.dp))
                        }
                        if (expandedId == item.id) {
                            Text(item.description)
                            Spacer(Modifier.height(8.dp))
                            TextButton(onClick = { onOpenDetail(item.id) }) {
                                Text("Ver detalhes")
                            }
                        } else {
                            Text(item.description.take(100) + if (item.description.length > 100) "..." else "")
                        }
                    }
                }
            }
        }
    }
}
