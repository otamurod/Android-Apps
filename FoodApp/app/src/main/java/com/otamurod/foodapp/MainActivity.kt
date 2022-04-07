package com.otamurod.foodapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.food_ticket.view.*

class MainActivity : AppCompatActivity() {

    var adapter:FoodAdapter ?= null
    var listOfFood = ArrayList<Food>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //load food
        listOfFood.add(Food("Coffee", "    Coffee is a brewed drink prepared from roasted coffee beans, the seeds of berries from certain Coffea species. From the coffee fruit, the seeds are separated to produce a stable, raw product: unroasted green coffee. The seeds are then roasted, a process which transforms them into a consumable product: roasted coffee, which is ground into a powder and typically steeped in hot water before being filtered out, producing a cup of coffee.", R.drawable.coffee_pot))
        listOfFood.add(Food("Espresso", "    Espresso (ess-PRESS-oh) is a full-flavored, concentrated form of coffee that is served in “shots.” It is made by forcing pressurized hot water through very finely ground coffee beans using an espresso machine. The result is a liquid stronger than coffee topped with a “crema,” a brown foam that forms when air bubbles combine with the soluble oils of fine-ground coffee and sits on top of a properly pulled shot of espresso. The crema adds to the rich flavor and lingering aftertaste of espresso.  Espresso is made using the exact same plant as coffee, and is grown, processed, and roasted the same way. Any origin and roast coffee can be used to make espresso. The difference between coffee and espresso is in the grind and the treatment of the beans. The beans are ground to a finer consistency than coffee and firmly packed before hot water is forced through using an espresso machine. This results in a shot of espresso, which can be enjoyed as-is or used to make a long list of drinks including a cappuccino or Americano.", R.drawable.espresso))
        listOfFood.add(Food("French Fries", "    A thin strip of potato, usually cut 3 to 4 inches in length and about 1/4 to 3/8 inches square that are deep fried until they are golden brown and crisp textured on the outside while remaining white and soft on the inside. French fries are not French in origin. They are referred to as French because of the way they are cut. Very thin strips may be referred to as shoestring potatoes and thicker strips are called steak fries. In Britain, french fries are referred to as chips.", R.drawable.french_fries))
        listOfFood.add(Food("Honey", "    Honey, sweet, viscous liquid food, dark golden in colour, produced in the honey sacs of various bees from the nectar of flowers. Flavour and colour are determined by the flowers from which the nectar is gathered. Some of the most commercially desirable honeys are produced from clover by the domestic honeybee. The nectar is ripened into honey by inversion of the major portion of its sucrose sugar into the sugars levulose (fructose) and dextrose (glucose) and by the removal of excess moisture.  Honey is stored in the beehive or nest in a honeycomb, a double layer of uniform hexagonal cells constructed of beeswax (secreted by the worker bees) and propolis (a plant resin collected by the workers). Honeycomb is used in winter as food for the larvae and other members of the colony. It is commonly sold by beekeepers as a delicacy, or the wax may be extracted for various purposes.", R.drawable.honey))
        listOfFood.add(Food("Strawberry", "    Strawberry ice cream is a flavor of ice cream made with strawberry or strawberry flavoring. It is made by blending in fresh strawberries or strawberry flavoring with the eggs, cream, vanilla and sugar used to make ice cream. Most strawberry ice cream is colored pink or light red. Strawberry ice cream dates back at least to 1813, when it was served at the second inauguration of James Madison. Along with vanilla and chocolate ice cream, strawberry is one of the three flavors in Neapolitan ice cream. Variations of strawberry ice cream include strawberry cheesecake ice cream and strawberry ripple ice cream, which is vanilla ice cream with a ribbon of strawberry jam or syrup. Some ice cream sandwiches are prepared neapolitan-style, and include strawberry ice cream.", R.drawable.strawberry_ice_cream))
        listOfFood.add(Food("Sugar Cubes", "    Sugar Cubes are cubes of sugar meant for table use. They allow a user of them to sweeten his or or beverage as desired. Generally, the beverages are hot, typically coffee or tea, as the cubes dissolve away very quickly into a liquid when the liquid is hot. They can be used to sweeten a cold drink, such as Iced Tea, but you will have to stir a great deal to encourage the cute to dissolve faster.  The cubes are made by pressing granulated sugar, mixed with a bit of sugar liquid to help glue them all together, into cube shapes.", R.drawable.sugar_cubes))

        adapter = FoodAdapter(this, listOfFood)

        gridViewListFood.adapter = adapter

    }

    class FoodAdapter:BaseAdapter{

        var listOfFood = ArrayList<Food>()
        var context:Context ?= null
        constructor(context: Context, listOfFood:ArrayList<Food>):super(){
            this.context = context
            this.listOfFood = listOfFood
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

            val food = this.listOfFood[p0]
            var inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var foodView = inflater.inflate(R.layout.food_ticket, null)
            foodView.imageViewFood.setImageResource(food.image!!)

            foodView.imageViewFood.setOnClickListener {
                val intent = Intent(context, FoodDetails::class.java)
                intent.putExtra("name", food.name!!)
                intent.putExtra("des", food.des!!)
                intent.putExtra("image", food.image!!)
                context!!.startActivity(intent)
            }

            foodView.textViewFood.text = food.name!!

            return foodView
        }

        override fun getCount(): Int {
            return listOfFood.size
        }

        override fun getItem(p0: Int): Any {
            return listOfFood[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

    }

}