async function updateTable() {
  try {
    //Get the table data
    let data = await $.getJSON("/get_data");
    //remove children from table
    $("#pizza_list").empty();
	$("#table_of_items th").remove(); 
    //Rebuild the table using jquery.
	
	if (data.length == 0) {
		$("#pizza_list").append($(`<p>No Pizzas Found</p>`));
	} else {
		$("#pizza_list").append($(`<tr><th></th><th>Address</th><th>Description</th><th>Price</th>`));
	}
	
    data.forEach((obj) => {
      console.dir(obj);
      let e = $("<tr>");
      let s = $("<span>");
	  let price = obj.total_price.toFixed(2);
      s.text("Delete");
      s.attr("id", `${obj._id}`);
      s.click(delete_clicked);
      e.append($("<td>").append(s));
      e.append($(`<td>${obj.address}</td>`));
	  e.append($(`<td>${createPizzaDescription(obj)}</td>`));
	  e.append($(`<td>$${price}</td>`));
      $("#pizza_list").append(e);
    })
  } catch (err) {
    console.log(err);
  }
}

async function delete_clicked(e) {
	//Add a delete route to index.js.  Pass it e.target.id something like this  
	// $.getJSON("/delete_entry?id=" + e.target.id);
	//alert("DELETE: " + e.target.id);
	await $.getJSON("/pizza_shanty_delete_one?_id=" + e.target.id);
	
	updateTable();
}

async function delete_all_clicked() {
	await $.getJSON("/pizza_shanty_delete_all");
	updateTable();
}

function createPizzaDescription(obj) {
	let pizzaStr = "";
	if (obj.extra_cheese == true)
		pizzaStr += 'Extra Cheese ';
	pizzaStr += `${obj.crust_size} ${obj.crust_type} Pizza`;
	
	if (obj.toppings != null) {
		pizzaStr += ' with ';
		
		if (obj.toppings[0].length <= 1)
			pizzaStr += obj.toppings;
		else {
			for (i = 0; i < obj.toppings.length - 1; i++) {
				pizzaStr += `${obj.toppings[i]}, `;
			}
			pizzaStr += obj.toppings[obj.toppings.length - 1];
		}
	}
	
	return pizzaStr;
}

$("#delete_all").click(delete_all_clicked);

$(updateTable());

