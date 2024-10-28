package lat.pam.utsproject

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class OrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_order)

        // Retrieve the food name and description from the Intent
        val foodName = intent.getStringExtra("FOOD_NAME")
        val foodDescription = intent.getStringExtra("FOOD_DESCRIPTION")

        // Initialize EditText and Button
        val etServings: EditText = findViewById(R.id.etServings)
        val etName: EditText = findViewById(R.id.etName)
        val etNotes: EditText = findViewById(R.id.etNotes)
        val btnOrder: Button = findViewById(R.id.btnOrder)

        // Initialize Spinner
        val spinnerFoodOptions: Spinner = findViewById(R.id.spinnerFoodOptions)

        // New data for Spinner (food options)
        val foodOptions = arrayOf(
            "Batagor",
            "Black Salad",
            "Cappucino",
            "Cheesecake",
            "Cireng",
            "Donut",
            "Kopi Hitam",
            "Mie Goreng",
            "Nasi Goreng",
            "Sparkling Tea"
        )

        // Set up ArrayAdapter for the spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, foodOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFoodOptions.adapter = adapter

        // Set previously selected food item in Spinner
        foodName?.let {
            val position = foodOptions.indexOf(it)
            if (position >= 0) {
                spinnerFoodOptions.setSelection(position)
            }
        }

        // Set up order button click listener
        btnOrder.setOnClickListener {
            val servings = etServings.text.toString()
            val orderingName = etName.text.toString()
            val notes = etNotes.text.toString()

            // Get selected food option from Spinner
            val selectedFoodOption = spinnerFoodOptions.selectedItem.toString()

            // Create Intent for ConfirmationActivity
            val intent = Intent(this, ConfirmationActivity::class.java).apply {
                putExtra("FOOD_NAME", selectedFoodOption)
                putExtra("SERVINGS", servings)
                putExtra("DESCRIPTION", foodDescription)
                putExtra("ORDERING_NAME", orderingName)
                putExtra("NOTES", notes)
            }
            startActivity(intent)
        }
    }
}
