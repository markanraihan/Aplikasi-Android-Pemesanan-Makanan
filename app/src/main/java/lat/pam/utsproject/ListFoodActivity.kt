package lat.pam.utsproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListFoodActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var foodList: List<Food> // Tetap private val

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_food)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Menyiapkan data makanan
        foodList = listOf(
            Food("Batagor", "Batagor asli enak dari Bandung", R.drawable.batagor),
            Food("Black Salad", "Salad segar yang dibuat secara langsung", R.drawable.black_salad),
            Food("Cappucino", "Kopi cappucino asli yang dibuat dari Kopi Arabica", R.drawable.cappuchino),
            Food("Cheesecake", "Kue keju yang lembut dan manis", R.drawable.cheesecake),
            Food("Cireng", "Camilan khas Bandung yang renyah", R.drawable.cireng),
            Food("Donut", "Donut manis yang empuk dan lezat", R.drawable.donut),
            Food("Kopi Hitam", "Kopi hitam pekat yang menggugah selera", R.drawable.kopi_hitam),
            Food("Mie Goreng", "Mie goreng pedas yang nikmat", R.drawable.mie_goreng),
            Food("Nasi Goreng", "Nasi goreng spesial yang menggoda", R.drawable.nasigoreng),
            Food("Sparkling Tea", "Teh segar dengan gelembung", R.drawable.sparkling_tea)
        )

        // Mengatur adapter dengan listener
        val adapter = FoodAdapter(foodList) { food ->
            val intent = Intent(this, OrderActivity::class.java).apply {
                putExtra("FOOD_NAME", food.name)
                putExtra("FOOD_DESCRIPTION", food.description)
                putExtra("FOOD_IMAGE", food.imageResourceId)
            }
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
