const db = require("./db.js");

// constructor
const Airport = function (Airport) {
    this.city = Airport.city;
    this.dueDate = Airport.dueDate;
    this.isDone = Airport.isDone;
};

//create a Airport
Airport.create = (newAirport, result) => {
    db.query("INSERT INTO airports SET ?", newAirport, (err, res) => {
        if (err) {
            console.log("error: ", err);
            result(err, null);
            return;
        }
        console.log("created airports: ", { code: res.insertId, ...newAirport });
        result(null, { code: res.insertId, ...newAirport });
    });
};

//return one airport by code
TodAirport.findById = (code, result) => {
    db.query(`SELECT * FROM airports WHERE code = ${code}`, (err, res) => {
        if (err) {
            console.log("error: ", err);
            result(err, null);
            return;
        }

        if (res.length) {
            console.log("found airports: ", res[0]);
            result(null, res[0]);
            return;
        }

        // not found Airports with the code
        result({ kind: "not_found" }, null);
    });
};

// return all airport[serach by city and return all if any]
Airport.getAll = (sortOrder, result) => {
    // var sql = "SELECT * FROM airports ORDER BY ??";
    // var inserts = [sortOrder];
    // var query = db.format(sql, inserts);

    var query = db.format("SELECT * FROM airports ORDER BY ??", [sortOrder]); // ?? represent a name of a field, ? represent a value
    // console.log(query);
    console.log("orderBy: " + sortOrder);
    db.query(query, [], (err, res) => {
        if (err) {
            console.log("error: ", err);
            result(null, err);
            return;
        }
        console.log("Airports: ", res);
        result(null, res);
    });
};

// Airport.getAllPublished = result => {
//     sql.query("SELECT * FROM Airports WHERE published=true", (err, res) => {
//         if (err) {
//             console.log("error: ", err);
//             result(null, err);
//             return;
//         }

//         console.log("Airports: ", res);
//         result(null, res);
//     });
// };

//update a airport
Airport.updateById = (code, airport, result) => {
    db.query(
        "UPDATE airports SET city = ?, dueDate = ?, isDone = ? WHERE code = ?",
        [airport.city, airport.dueDate, airport.isDone, code],
        (err, res) => {
            if (err) {
                console.log("error: ", err);
                result(null, err);
                return;
            }

            if (res.affectedRows == 0) {
                // not found Airports with the code
                result({ kind: "not_found" }, null);
                return;
            }

            console.log("updated Airports: ", { code: code, ...airport });
            result(null, { code: code, ...airport });
        }
    );
};

//delete a airport
Airport.remove = (code, result) => {
    db.query("DELETE FROM Airports WHERE code = ?", code, (err, res) => {
        if (err) {
            console.log("error: ", err);
            result(null, err);
            return;
        }

        if (res.affectedRows == 0) {
            // not found Airports with the code
            result({ kind: "not_found" }, null);
            return;
        }

        console.log("deleted Airports with code: ", code);
        result(null, res);
    });
};

// Airport.removeAll = result => {
//     sql.query("DELETE FROM Airports", (err, res) => {
//         if (err) {
//             console.log("error: ", err);
//             result(null, err);
//             return;
//         }

//         console.log(`deleted ${res.affectedRows} Airports`);
//         result(null, res);
//     });
// };

module.exports = Airport;