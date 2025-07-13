const express = require('express');
const app = express();
//const {MongoClient} = require('mongodb');
const mongo = require('mongodb');
const MongoClient = mongo.MongoClient;
const uri = "mongodb://user123:user123@localhost:4260/guestbook";

app.set('view engine', 'pug');

app.listen(3025);

app.get("", guestbook);
app.get('/', guestbook);
app.get('/guestbook', guestbook);
app.get("/guestbook_store",  (req, res) => {  
  //Turn URL get data into a JavaScript object
  const urlObj = req.query;
  let obj = {};
  try {
	  let result = saveToMongo(urlObj);
      if (result.insertedId) {
         obj.header="Thank you";
		 obj.message="Your comments have been saved";
	  }
  } catch(err) {
     obj.header="Thank you";
	 obj.message="Sorry. There was an error connecting to database";
  }
  showGuestbook(res, obj);  
});

app.get("/guestbook_delete", async (req, res) => {
    const client = new MongoClient(uri);
    try {
        await client.connect();
        console.log("Connected to server");

        const coll = await client.db("guestbook").collection("guestbook");

        // Validate ObjectId before using it
        if (!mongo.ObjectId.isValid(req.query.value)) {
            throw new Error("Invalid ObjectId");
        }

        const result = await coll.deleteOne({_id: new mongo.ObjectId(req.query.value)});

        let obj = {};
        if (result.deletedCount > 0) {
            obj = {title: "Guestbook", message: "Object Delete"};
        } else {
            obj = {title: "Guestbook", message: "Object Not Delete"};
        }
        await showGuestbook(res, obj, client);
    } catch (err) {
        console.error(err);
        res.status(500).send("Internal Server Error");
    } finally {
        await client.close();
        console.log("Connection closed");
    }
});


app.get("/guestbook_view", async (req, res) => {
	showGuestbook(res, '');
});

app.get("/guestbook_test", async (req, res) => {
	app.locals.title = "Guestbook";
	app.locals.header = "This is a Test";
	app.locals.message = "This is a note from my doctor saying I don't have to pay.";
	app.locals.gb[{first: "Joel", last:"Swanson", comment: "Hi from pug!"}];
	res.render('basic.pug');
});

async function showGuestbook(res, obj, client = new MongoClient(uri)) {
	
	try {
	// Connect to the MongoDB
        await client.connect();
        console.log("Connected successfully to server");
        const coll = client.db("guestbook").collection('guestbook');
        const cursor = coll.find();
        const results = await cursor.toArray();
        if (results.length > 0) {
		   obj.gb = results;
			 
        } else {
           obj.messsage='No guestbook entries found.';
        }
		res.render('basic.pug', obj)
    } finally {
        // Close the connection to the MongoDB cluster
        await client.close();
        console.log("Connection closed");
    }

}
     

async function saveToMongo(urlObj) {
   const uri = "mongodb://user123:user123@localhost:4260/guestbook";
   const client = new MongoClient(uri);

   try {
     await client.connect();
     console.log("Connected successfully to server");
     const db = client.db("guestbook");
     let result = await db.collection("guestbook").insertOne(urlObj);
     return result;

 } finally {
    await client.close();
    console.log("Close Connected");
  }
}
app.use(express.static('public'));
app.use(guestbookError);

function guestbook(req, res) {
    res.redirect(301, '/guestbook.html');
}

function guestbookError(req, res) {
    res.status(401);
	let obj = {
		title: "Guestbook Error",
		header: "401 Page not found",
		message: "The resource you requested cant be found."
    }		
	res.render('basic.pug', obj)
}

function createBasePage(body) {
    let html = "";
    html += "<!DOCTYPE html>\n";
    html += "<html lang='en'>\n";
    html += "<head>\n";
    html += "<meta charset='utf-8'>\n";
    html += "<title>required</title>\n";
    html += "</head>\n";
    html += "<body bgcolor='goldenrod'>\n";
    html += body;
    html += "</body>\n"
    html += "</html>\n"
    return html;
}
