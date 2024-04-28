package com.example.messlunch.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.messlunch.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.compose.AppTheme
import com.example.messlunch.Datasource.Data
import com.example.messlunch.Model.Entree
import com.example.messlunch.Model.OrderUiState


@Composable
fun EntreeMenuScreen(
    options: List<Entree>,
    modifier: Modifier = Modifier,
    onCancelButtonClicked: () -> Unit = {},
    onNextButtonClicked: () -> Unit = {},
    orderviewModel: OrderViewModel = OrderViewModel()
){

    val uiState by orderviewModel.uiState.collectAsState()
    val subtotal = uiState.itemtotalPrice

    Surface(
        modifier=Modifier
        .fillMaxSize()
    ){
        Column(modifier = modifier) {

            options.forEach { item->
                ItemInRow(
                    item=item
                )
            }

            Spacer(modifier = Modifier.weight(1f))

                Text(text = stringResource(R.string.subtotal,subtotal.toString()),
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = 40.dp))

                Spacer(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                )



            MenuScreenButtonGroup(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 20.dp),
                onCancelButtonClicked = onCancelButtonClicked,
                onNextButtonClicked = onNextButtonClicked)

        }

    }



}



@Composable
fun ItemInRow(
    modifier: Modifier = Modifier,
    item :Entree,
    orderviewModel: OrderViewModel = OrderViewModel()
)
{
    val orderstate = orderviewModel.uiState.collectAsState()
    val entreeItems = orderstate.value.entreeItems.toMutableMap()
    var quantity by remember { mutableIntStateOf(entreeItems[item] ?: 0) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_medium))
    )
    {
        Column(
            modifier = Modifier.width(200.dp)
            ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 20.sp
                )
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black.copy(alpha = 0.36f))
             }

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text =item.formattedPrice(),
            style = MaterialTheme.typography.bodySmall,
            fontSize = 16.sp)

        Spacer(modifier = Modifier.width(32.dp))

        //box
        ItemQuantityButton(
           onincrease = {
               quantity = quantity.plus(1)
               orderviewModel.updateEntree(item,quantity)
                        },
            ondecrease = {
                quantity = quantity.minus(1)
                orderviewModel.updateEntree(item,quantity)
                         },
            quantitytext = quantity.toString()
        )

    }

    Divider(
        thickness = dimensionResource(R.dimen.thickness_divider),
        modifier = Modifier.padding(start = 8.dp,end=8.dp)
    )
}

@JvmOverloads
@Composable
fun ItemQuantityButton(
    modifier: Modifier = Modifier,
    quantitytext:String = "0",
    onincrease : () ->Unit,
    ondecrease : () -> Unit

) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .clickable { ondecrease() }
                .padding(end = 8.dp, start = 8.dp),
            text = "-",
            fontSize = 20.sp,
            color = Color.Black
        )

        Box(
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = Color.Black,
                    shape = RectangleShape
                )
                .height(20.dp)
                .width(32.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text=quantitytext,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Text(
            text = "+",
            modifier = Modifier
                .clickable { onincrease() }
                .padding(start = 8.dp, end = 8.dp),
            fontSize = 20.sp)


    }
}

@Composable
fun MenuScreenButtonGroup(
    onCancelButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
){

        Row(modifier = modifier,
            horizontalArrangement = Arrangement.Absolute.SpaceBetween){

            OutlinedButton(
                onClick = onCancelButtonClicked,
            ) {
                Text(
                    text = stringResource(R.string.cancel).uppercase(),
                    color = Color.Red)
            }
            Spacer(modifier = Modifier.width(40.dp))
            Button(onClick = onNextButtonClicked) {
                Text(text = stringResource(R.string.next).uppercase())

            }
        }


}



@Preview(showBackground = true)
@Composable
fun PreviewItem(){
   AppTheme {
      EntreeMenuScreen(options = Data.entreeItems)
   }
}