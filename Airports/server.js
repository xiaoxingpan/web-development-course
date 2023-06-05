//Express is for building the Rest apis
const express = require("express");

//const nocache = 

//cors provides Express middleware to enable CORS with various options.
const cors = require("cors");

//create an Express app
const app = express();

//logger npmlog
const logger = require("npmlog");

//origin
var corsOptions = {
    origin: "http://localhost:8081"
};

app.use(cors(corsOptions));

// parse requests of content-type - application/json
app.use(express.json());

// parse requests of content-type - application/x-www-form-urlencoded
app.use(express.urlencoded({ extended: true }));

// simple route
// app.get("/", (req, res) => {
//     res.json({ message: "Welcome to day02todos application." });
// });

require("./app/routes/todo.routes.js")(app);

app.use(express.static('static'));

// set port, listen for requests
const PORT = process.env.PORT || 8080;
app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}.`);
});