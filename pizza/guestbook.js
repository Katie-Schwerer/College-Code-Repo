const express = require('express');
const app = express();
const {MongoClient} = require('mongodb');

app.listen(3050);

app.get("", guestbook);
app.get('/', guestbook);
app.get('guestbook', guestbook);

app.use(express.static('public'));
app.use(guestbookError);

app.get("/guestbook_store", async  (req, res) => {
    const urlObj = req.query;
    let result = await saveToMongo(urlObj);
    let body;
    if (result.insertedId) {
        body += "<p>Your comments was saved</p>\n"
    } else {
        body += "<p>Sorry. There was an error saving your data</p>\n";
    }
    showGuestbook(res, body);
});

function showGuestbook(body) {
    res.send(createHTMLPage(body));
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
    }
}

function guestbook(req, ) {
}

function guestbookError(req, res) {
    res.status(401);
    res.end(createBasePage("<h1>401 Not Found</h1>\n"));
}

function createBasePage(body) {
    let html = "<!DOCTYPE html>\n"
    html += "<html lang='en'>\n"
    html += "<head>\n"
    html += "<meta charset='utf-8'>\n"
    html += "<title>required</title>\n"
    html += "</head>\n"
    html += "<body bgcolor='goldenrod'>\n"
    html += body
    html += "</body>\n"
    html += "</html>\n"
    return html;
}
