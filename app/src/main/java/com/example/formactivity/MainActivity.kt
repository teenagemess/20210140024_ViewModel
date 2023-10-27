package com.example.formactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.formactivity.Data.CobaViewModel
import com.example.formactivity.Data.DataForm
import com.example.formactivity.Data.DataSource.jenis
import com.example.formactivity.Data.DataSource.status
import com.example.formactivity.ui.theme.FormActivityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FormActivityTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TampilLayout()
                }
            }
        }
    }
}

@Composable
fun TampilLayout(modifier: Modifier = Modifier){
    val image = painterResource(id = R.drawable.back)
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()

        ) {
         Image(painter = image, contentDescription = "" )
            Text(
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 140.dp) ,
                text = "Register")
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth()
        ){
            Text(
                fontSize = 30.sp,
                text = "Create your account",
                fontWeight = FontWeight.Bold)
        }
        TampilForm()
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TampilForm(cobaViewModel: CobaViewModel = viewModel()){
    var  textNama by remember { mutableStateOf("")}
    var texttlp by remember { mutableStateOf("")}
    var textAlamat by remember { mutableStateOf("")}
    var textEmail by remember { mutableStateOf("")}


    val context = LocalContext.current
    val contextt = LocalContext.current
    val dataForm : DataForm
    val uiState by cobaViewModel.uiState.collectAsState()
    dataForm = uiState

    OutlinedTextField(
        value = textNama,
        onValueChange = {textNama = it},
        label = { Text(text = "Nama Lengkap")},
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .fillMaxWidth(),
        placeholder = { Text(text = "Masukkan nama anda")}
    )
    OutlinedTextField(
        value = texttlp,
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = {texttlp = it},
        label = { Text(text = "Nomor telpon")},
        modifier = Modifier
            .fillMaxWidth(),
        placeholder = { Text(text = "Masukkan nomor telpon anda")}
    )
    OutlinedTextField(
        value = textEmail,
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        onValueChange = {textEmail = it},
        label = { Text(text = "Email")},
        modifier = Modifier
            .fillMaxWidth(),
        placeholder = { Text(text = "Masukkan Email anda")}
    )

    SelectJK(
        options = jenis.map{ id -> context.resources.getString(id)},
        onSelectedChanged = {cobaViewModel.setJK(it)})



    OutlinedTextField(
        value = textAlamat,
        onValueChange = {textAlamat = it},
        label = { Text(text = "Alamat")},
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .fillMaxWidth(),
        placeholder = { Text(text = "Masukkan alamat anda")}
    )
    ElevatedButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = {cobaViewModel.BacaData(textNama,texttlp,dataForm.sex,textAlamat,textEmail, dataForm.stats)
    }){
        Text(text = stringResource(R.string.submit),
            fontSize = 16.sp)
    }
    Spacer(modifier = Modifier.height(20.dp))
    TextHasil(
        namanya = cobaViewModel.namaUsr,
        telponnya =cobaViewModel.noTlp ,
        jenisnya = cobaViewModel.jenisKl,
        alamatnya = cobaViewModel.alamat,
        emailnya = cobaViewModel.email
    )
}

@Composable
fun SelectJK(
    options: List<String>,
    onSelectedChanged: (String) -> Unit = {}) {

    var selectedValue by rememberSaveable { mutableStateOf("")
    }
    Column (modifier = Modifier.padding(16.dp)) {
        options.forEach { item ->
            Row (
                modifier = Modifier.selectable(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectedChanged(item)
                    }
                ),
                verticalAlignment = Alignment.CenterVertically
            ){
                RadioButton(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectedChanged(item)
                    }
                )
                Text(item)
            }
        }
    }
}

@Composable
fun SelectNikah(
    options: List<String>,
    onSelectedChanged: (String) -> Unit = {}) {

    var selectedValue by rememberSaveable { mutableStateOf("")
    }
    Column (modifier = Modifier.padding(16.dp)) {
        options.forEach { item ->
            Row (
                modifier = Modifier.selectable(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectedChanged(item)
                    }
                ),
                verticalAlignment = Alignment.CenterVertically
            ){
                RadioButton(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectedChanged(item)
                    }
                )
                Text(item)
            }
        }
    }
}

@Composable
fun TextHasil(namanya:String,telponnya:String,jenisnya:String,alamatnya:String, emailnya:String){
    ElevatedCard (
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
    ){
        Text(text = "Nama : " + namanya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp))
        Text(text = "Telepon : " + telponnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp))
        Text(text = "Jenis Kelamin : " + jenisnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp))
        Text(text = "Alamat : " + alamatnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp))
        Text(text = "Email : " + emailnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp))
    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FormActivityTheme {
        TampilLayout()
    }
}