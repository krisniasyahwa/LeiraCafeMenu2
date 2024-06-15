//package org.d3if3087.leiracafemenu2.ui.screen
//
//
//import androidx.compose.material3.AlertDialog
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextButton
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.res.stringResource
//import org.d3if3087.leiracafemenu2.R
//import org.d3if3087.leiracafemenu2.model.Menu
//
//@Composable
//fun DeleteDialog(menu: Menu, onDismissRequest: () -> Unit, onConfirmation: (Long) -> Unit, id: Long) {
//    AlertDialog(
//        onDismissRequest = { onDismissRequest() },
//        title = {
//            Text(text = "Want to delete this ${menu.name} item ?")
//        },
//        confirmButton = {
//            TextButton(onClick = {
//                onConfirmation(id)
//            }) {
//                Text(text = stringResource(id = R.string.delete))
//            }
//        },
//        dismissButton = {
//            TextButton(onClick = { onDismissRequest() }) {
////                Text(text = stringResource(id = R.string.cancel))
//            }
//        }
//    )
//}