var express = require('express');
var router = express.Router();
const mongo = require('mongodb');
const MongoClient = require('mongodb').MongoClient;

/* GET home page. */

router.post('/pizza_shanty_save', async function(req, res, next) {
	let obj = {};
	obj.title = 'Pizza Review';
	
	delete req.body.tip;
	req.body.total_price = Number(req.body.total_price);
	
	if (req.body.extra_cheese == 'true') 
		req.body.extra_cheese = true;
	else
		req.body.extra_cheese = false;
	
	const uri = "mongodb://pizza_user:pizza_user@localhost:4260/pizza_shanty_db";
    const client = new MongoClient(uri);
    try {
        // Connect to the MongoDB
        await client.connect();
        console.log("Connected successfully to server");
        // Do the work here.

        const db = client.db('pizza_shanty_db');
        const result = await db.collection('pizzas').insertOne(req.body);
        console.log(`New listing created with the following id: ${result.insertedId}`);
		if (result.insertedId != null)
			obj.header = 'Your pizza is on the way!';
		else 
			obj.header = 'Your order could not be processed.';

    } finally {
        // Close the connection to the MongoDB cluster
        await client.close();
        console.log("Connection closed");
    }
	
	console.log(req.body);
	
	let pizzaStr = "";
	if (req.body.extra_cheese == true)
		pizzaStr += 'Extra Cheese ';
	pizzaStr += `${req.body.crust_size} ${req.body.crust_type} Pizza`;
	
	if (req.body.toppings != null) {
		pizzaStr += ' with ';
		
		if (req.body.toppings[0].length <= 1)
			pizzaStr += req.body.toppings;
		else {
			for (i = 0; i < req.body.toppings.length - 1; i++) {
				pizzaStr += `${req.body.toppings[i]}, `;
			}
			pizzaStr += req.body.toppings[req.body.toppings.length - 1];
		}
	}
	
	obj.message = pizzaStr;
	
	res.render('view.pug', obj);
});

router.get('/pizza_shanty_view', function(req, res, next) {
	res.render('list.pug', {title:'View Pizzas'});
});

router.get('/get_data', async function(req, res, next) {
	//for pizza shanty, get data from mongo database
	const uri = "mongodb://pizza_user:pizza_user@localhost:4260/pizza_shanty_db";
    const client = new MongoClient(uri);
	let results = [];
    try {
        // Connect to the MongoDB
        await client.connect();
        console.log("Connected successfully to server");
        // Do the work here.

        const coll = client.db('pizza_shanty_db').collection('pizzas');
		const cursor = coll.find();
		results = await cursor.toArray();

    } finally {
        // Close the connection to the MongoDB cluster
        await client.close();
        console.log("Connection closed");
    }
	
	res.json(results);
});

router.get('/pizza_shanty_delete_one', async function(req, res, next) {
	//for pizza shanty, get data from mongo database
	const uri = "mongodb://pizza_user:pizza_user@localhost:4260/pizza_shanty_db";
    const client = new MongoClient(uri);
	
	let result;
	
    try {
        // Connect to the MongoDB
        await client.connect();
        console.log("Connected successfully to server");
        // Do the work here.

        const coll = client.db('pizza_shanty_db').collection('pizzas');
		result = await coll.deleteOne({_id:new mongo.ObjectId(req.query._id)});

    } finally {
        // Close the connection to the MongoDB cluster
        await client.close();
        console.log("Connection closed");
    }
	res.json(result);
});
		
router.get('/pizza_shanty_delete_all', async function(req, res, next) {
	//for pizza shanty, get data from mongo database
	const uri = "mongodb://pizza_user:pizza_user@localhost:4260/pizza_shanty_db";
    const client = new MongoClient(uri);
	
	let result;
	
    try {
        // Connect to the MongoDB
        await client.connect();
        console.log("Connected successfully to server");
        // Do the work here.

        const coll = client.db('pizza_shanty_db').collection('pizzas');
		result = await coll.deleteMany({});

    } finally {
        // Close the connection to the MongoDB cluster
        await client.close();
        console.log("Connection closed");
    }
	res.json(result);
});

router.get('/pizza_shanty', function(req, res, next) {
	res.redirect(301, '/');
});

router.get('/pizza_shanty.html', function(req, res, next) {
	res.redirect(301, '/');
});

router.use(express.static('public'));

module.exports = router;
