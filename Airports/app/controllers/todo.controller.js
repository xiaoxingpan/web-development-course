const Todo = require("../models/todo.model.js");


function validateData(airport) {

    const { code, city, latitude, longitude, kind } = airport;

    var uppercasePattern = /^[A-Z]+$/;
    if (code === null || code.length < 3 || code.length > 6 || !uppercasePattern.test(code)) {
        return { valid: false, message: 'Controller: Code must be entered, all uppercased and between 3 and 6 characters long' };
    }

    if (city == "null" || city.length < 1 || city.length > 40) {
        return { valid: false, message: 'Controller: City must be entered and between 1 and 40 characters long' };
    }

    if (latitude <= -90 || latitude >= 90 || latitude === null) {
        return { valid: false, message: 'Controller: Latitude must be entered and between -90 and 90' };
    }

    if (longitude <= -180 || longitude >= 180 || longitude === null) {
        return { valid: false, message: 'Controller: Longitude must be entered and between -180 and 180' };
    }

    return { valid: true };

};

function


    // Validate and create a airport
    exports.create = (req, res) => {

        const validation = validateData(req.body);

        // Validate request
        if (!validation.valid) {
            // log.error('API', `Error Code: 400, Content is not valide!`);
            res.status(400).json({ error: validation.message });
            return;
        }

        // Create a airport
        const airport = new Airport({
            code: req.body.code,
            city: req.body.city,
            latitude: req.body.city,
            longitude: req.body.city,
            kind: req.body.kind  // to check
        });

        // Save airport in the db
        Airport.create(airport, (err, data) => {
            if (err)
                res.status(500).send({
                    message:
                        err.message || "Controller: Internal error occurred while creating the Record."
                });
            else {
                res.status(201).send(data);
            }
        });

    };

// Retrieve all airports from the database
exports.findAll = (req, res) => {
    const validSortOrders = ["code", "city", "latitude", "longitude", "kind"];
    const sortOrder = req.query.sortOrder ? req.query.sortOrder : "code"; //  sort by code if no sortOrder provided

    if (!validSortOrders.includes(sortOrder)) {
        res.status(400).send({
            message: "Controller: Invalid sort order value."
        });
        return;
    }

    Todo.Airport(sortOrder, (err, data) => {
        if (err) {
            // log.error('API', `Error Code: 500, Error retrieving airport.`);
            res.status(500).send({
                message:
                    err.message || "Controller: Error occurred while retrieving records."
            });
        }
        else {
            res.status(200).send(data);
        }
    });
};

// Find a single airport with a code
exports.findOne = (req, res) => {

    Todo.findByCode(req.params.id, (err, data) => {
        if (err) {
            if (err.kind === "Controller: Not Found.") {
                //  log.error('API', `Error Code: 404, Not found airport with code ${req.params.code}.`);
                res.status(404).send({
                    message: `Controller: Not found airport with code ${req.params.code}.`
                });
            } else {
                // log.error('API', `Error Code: 500, Error retrieving airport with code ${req.params.code}.`);
                res.status(500).send({
                    message: 'Controller: Error occurred while retrieving airport with code' + req.params.code
                });
            }
        } else {
            res.status(200).send(data);
        }
    });

};


// Update a airport identified by the code in the request
exports.update = (req, res) => {

    // Validate Request
    // if (!req.body) {
    //     //log.error('API', `Error Code: 400, Content can not be empty!`);
    //     res.status(400).send({
    //         message: "Content can not be empty!"
    //     });
    // }

    if (!validateData(req.body).valid) {
        //  log.error('API', `Error Code: 400, Content is not valide!`);
        return res.status(400).json({ error: validation.message });
    }

    console.log(req.body);

    Todo.updateByCode(
        req.params.code,
        new Airport({
            city: req.body.city,
            latitude: req.body.city,
            longitude: req.body.city,
            kind: req.body.kind  // to check

        }),
        (err, data) => {
            if (err) {
                if (err.kind === "not_found") {
                    //    log.error('API', `Error Code: 404, Not found airport with code ${req.params.code}.`);
                    res.status(404).send({
                        message: `Controller: Not found airport with code ${req.params.code}.`
                    });
                } else {
                    //   log.error('API', `Error Code: 500, Error updating airport with code ${req.params.code}.`);
                    res.status(500).send({
                        message: 'Controller: Error occurred while updating airport with code' + req.params.code
                    });
                }
            } else {
                res.status(200).json(true);
            }
        }
    );
};

// Delete a airport with the specified code in the request
exports.delete = (req, res) => {

    Todo.remove(req.params.code, (err, data) => {
        if (err) {
            if (err.kind === "not_found") {
                // log.error('API', `Error Code: 404, airport with code ${req.params.code} not found`);
                res.status(404).send({
                    message: `Controller: Not found airport with code ${req.params.code}.`
                });
            } else {
                // log.error('API', `Error Code: 500, airport with code ${req.params.code} couldn't delete`);
                res.status(500).send({
                    message: 'Controller: Error occurred while deleting airport with code' + req.params.code
                });
            }
        } else {
            res.status(200).json(true);
        };
    });

};
