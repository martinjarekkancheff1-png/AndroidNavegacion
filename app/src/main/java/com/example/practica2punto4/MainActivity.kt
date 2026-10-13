package com.example.practica2punto4

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.practica2punto4.ui.theme.Practica2Punto4Theme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            Practica2Punto4Theme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "login"
                ) {

                    composable("login") {
                        LoginScreen(navController)
                    }

                    composable("menu") {
                        MenuScreen(navController)
                    }

                    composable("formulario") {
                        FormularioScreen(navController)
                    }

                    composable("lista") {
                        ListaScreen(navController)
                    }
                }
            }
        }
    }
}

@Composable
fun LoginScreen(navController: NavController) {

    val context = LocalContext.current

    var usuario by remember {
        mutableStateOf("")
    }

    var contrasena by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        horizontalAlignment = Alignment.Start,

        verticalArrangement = Arrangement.Top
    ) {

        Text(
            text = "Inicio de Sesión",
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,

            modifier = Modifier.align(
                Alignment.CenterHorizontally
            )
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = "usuario:"
        )

        TextField(
            value = usuario,

            onValueChange = {
                usuario = it
            },

            placeholder = {
                Text("Ingresa tu usuario")
            },

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = "contrasena:"
        )

        TextField(
            value = contrasena,

            onValueChange = {
                contrasena = it
            },

            placeholder = {
                Text("Ingresa tu contrasena")
            },

            modifier = Modifier.fillMaxWidth(),

            visualTransformation =
                PasswordVisualTransformation()
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Button(
            onClick = {

                Toast.makeText(
                    context,
                    "Bienvenido $usuario",
                    Toast.LENGTH_SHORT
                ).show()

                navController.navigate("menu")
            },

            modifier = Modifier.align(
                Alignment.End
            )
        ) {

            Text("Iniciar sesión")
        }
    }
}




@Composable
fun MenuScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        horizontalAlignment =
            Alignment.CenterHorizontally,

        verticalArrangement =
            Arrangement.Center
    ) {

        Text(
            text = "MENÚ PRINCIPAL",

            fontSize = 24.sp,

            fontWeight =
                FontWeight.ExtraBold
        )

        Spacer(
            modifier = Modifier.height(30.dp)
        )

        Button(
            onClick = {

                navController.navigate(
                    "formulario"
                )
            },

            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Formulario")
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Button(
            onClick = {

                navController.navigate(
                    "lista"
                )
            },

            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Lista")
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Button(
            onClick = {

                navController.navigate("login") {

                    popUpTo("login") {
                        inclusive = true
                    }
                }
            },

            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Cerrar sesión")
        }
    }
}



@Composable
fun FormularioScreen(navController: NavController) {

    val context = LocalContext.current

    data class Opcion(
        val value: String,
        val label: String
    )

    val proveedores = listOf(

        Opcion("1", "Gamesa"),
        Opcion("2", "Sabritas"),
        Opcion("3", "Coca Cola"),
        Opcion("4", "Bimbo")
    )

    var proveedorExpandido by remember {
        mutableStateOf(false)
    }

    var proveedor by remember {
        mutableStateOf(proveedores[0])
    }

    var nombreproducto by remember {
        mutableStateOf("")
    }

    var precio by remember {
        mutableStateOf("")
    }


    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        horizontalAlignment =
            Alignment.Start,

        verticalArrangement =
            Arrangement.Top
    ) {

        Text(
            text = "PRODUCTO",

            fontSize = 20.sp,

            fontWeight =
                FontWeight.ExtraBold,

            modifier = Modifier.align(
                Alignment.CenterHorizontally
            )
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )


        // NOMBRE

        Text(
            text = "NOMBRE:"
        )

        TextField(

            value = nombreproducto,

            onValueChange = {
                nombreproducto = it
            },

            placeholder = {
                Text("Ingresa el nombre")
            },

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )




        Text(
            text = "PRECIO:"
        )

        TextField(

            value = precio,

            onValueChange = {
                precio = it
            },

            placeholder = {
                Text("Ingresa el precio")
            },

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )


        // PROVEEDOR

        Text(
            text = "Proveedor:"
        )

        Box {

            Button(

                onClick = {

                    proveedorExpandido =
                        !proveedorExpandido
                },

                modifier =
                    Modifier.fillMaxWidth()
            ) {

                Text(
                    text = proveedor.label
                )
            }


            DropdownMenu(

                expanded =
                    proveedorExpandido,

                onDismissRequest = {

                    proveedorExpandido =
                        false
                }
            ) {

                proveedores.forEach { opcion ->

                    DropdownMenuItem(

                        text = {

                            Text(
                                opcion.label
                            )
                        },

                        onClick = {

                            proveedor =
                                opcion

                            proveedorExpandido =
                                false
                        }
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )




        Button(

            onClick = {

                Toast.makeText(
                    context,
                    "Nombre producto: $nombreproducto",
                    Toast.LENGTH_SHORT
                ).show()

                Toast.makeText(
                    context,
                    "Precio: $precio",
                    Toast.LENGTH_SHORT
                ).show()

                Toast.makeText(
                    context,
                    "Proveedor: ${proveedor.label}",
                    Toast.LENGTH_SHORT
                ).show()
            },

            modifier = Modifier.align(
                Alignment.End
            )
        ) {

            Text("Enviar")
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )


        // BOTÓN MENÚ

        Button(

            onClick = {

                navController.navigate(
                    "menu"
                )
            },

            modifier = Modifier.align(
                Alignment.End
            )
        ) {

            Text("Menú")
        }
    }
}



@Composable
fun ListaScreen(navController: NavController) {

    val scrollState =
        rememberScrollState()


    data class Producto(

        val nombreproducto: String,

        val precio: Double,

        val categoria: String
    )


    val productos = remember {

        mutableStateListOf(

            Producto(
                "Sponch Fresa",
                23.0,
                "galletas"
            ),

            Producto(
                "Emperador Combinado",
                22.0,
                "galletas"
            ),

            Producto(
                "Florentinas Cajeta",
                20.0,
                "galletas"
            ),

            Producto(
                "Coca Cola 600 ml",
                20.0,
                "refresco"
            )
        )
    }


    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .horizontalScroll(
                scrollState
            ),

        horizontalAlignment =
            Alignment.Start,

        verticalArrangement =
            Arrangement.Top
    ) {

        Text(

            text = "LISTA DE PRODUCTOS",

            fontSize = 20.sp,

            fontWeight =
                FontWeight.ExtraBold
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )




        Row {

            Text(
                text = "Nombre",

                modifier =
                    Modifier.width(150.dp),

                fontWeight =
                    FontWeight.Bold
            )

            Text(
                text = "Precio",

                modifier =
                    Modifier.width(100.dp),

                fontWeight =
                    FontWeight.Bold
            )

            Text(
                text = "Categoria",

                modifier =
                    Modifier.width(100.dp),

                fontWeight =
                    FontWeight.Bold
            )

            Text(
                text = "Eliminar",

                modifier =
                    Modifier.width(100.dp),

                fontWeight =
                    FontWeight.Bold
            )
        }


        HorizontalDivider()


        productos.forEachIndexed {
                index, producto ->

            val bgColor =

                if (index % 2 == 0) {

                    Color(0xFFF5F5F5)

                } else {

                    Color.White
                }


            Row(

                modifier = Modifier
                    .background(bgColor)
                    .padding(
                        vertical = 4.dp
                    )
            ) {

                Text(

                    text =
                        producto.nombreproducto,

                    modifier =
                        Modifier.width(150.dp)
                )

                Text(

                    text =
                        "$ ${producto.precio}",

                    modifier =
                        Modifier.width(100.dp)
                )

                Text(

                    text =
                        producto.categoria,

                    modifier =
                        Modifier.width(100.dp)
                )

                Button(

                    onClick = {

                        productos.removeAt(
                            index
                        )
                    }
                ) {

                    Text("Eliminar")
                }
            }
        }


        Spacer(
            modifier = Modifier.height(20.dp)
        )



        Button(

            onClick = {

                navController.navigate(
                    "menu"
                )
            }
        ) {

            Text("Menú")
        }
    }
}



@Preview(
    showBackground = true
)
@Composable
fun LoginPreview() {

    Practica2Punto4Theme {

        LoginScreen(
            rememberNavController()
        )
    }
}