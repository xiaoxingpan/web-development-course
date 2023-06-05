const Todo = require("../models/todo.model.js");
const dateFormat = 'YYYY-MM-DD';
const moment = require('moment');

// Create a single validation function that can be used for both to avoid extensive code duplication (DRY principle).
// - id must not be supplied
// - task must be 1-100 characters long
// - dueDate year must be between 1900 and 2099, date must parse as valid yyyy-mm-dd format
// - isDone is either 'Pending' or 'Done' 

function validateData(todo) {

    const { task, dueDate, isDone } = todo;

    // if (todo.id) {
    //     return { valid: false, message: 'ID must not be supplied' };
    // }

    if (!task || typeof task !== 'string' || task.length < 1 || task.length > 100) {
        return { valid: false, message: 'Task must be a string between 1 and 100 characters long' };
    }
    if (!moment(dueDate, dateFormat, true).isValid()) { // !dueDate || !/^\d{4}-\d{2}-\d{2}$/.test(dueDate)
        return { valid: false, message: 'Due date must be a valid date in the format yyyy-mm-dd' };
    }
    const year = parseInt(dueDate.substr(0, 4));
    if (year < 1900 || year > 2099) {
        return { valid: false, message: 'Due date year must be between 1900 and 2099' };
    }
    if (!["Pending", "Done"].includes(isDone)) { // isDone !== 'Pending' && isDone !== 'Done'

        return { valid: false, message: 'isDone must be either "Pending" or "Done"' };
    }
    return { valid: true };
};

function formatDate(date) {
    return moment(date).format('YYYY-MM-DD');
}

// Validate and create a todo
exports.create = (req, res) => {
    const validation = validateData(req.body);
    // Validate request
    if (!validation.valid) {
        // log.error('API', `Error Code: 400, Content is not valide!`);
        res.status(400).json({ error: validation.message });
        return;
    }
    // Create a Todos
    const todo = new Todo({
        // id: req.body.id,
        task: req.body.task,
        dueDate: moment(req.body.dueDate).format(dateFormat),
        isDone: req.body.isDone || 'Pending'
    });

    // Save Todos in the database
    Todo.create(todo, (err, data) => {
        if (err)
            res.status(500).send({
                message:
                    err.message || "Internal error occurred while creating the Record."
            });
        else {
            res.status(201).send(data);
        }
    });
};

// Retrieve all Todos from the database
exports.findAll = (req, res) => {
    //const task = req.query.task;
    const validSortOrders = ["id", "task", "dueDate", "isDone"];
    const sortOrder = req.query.sortOrder ? req.query.sortOrder : "id"; //  sort by id if no sortOrder provided

    if (!validSortOrders.includes(sortOrder)) {
        res.status(400).send({
            message: "invalid sort order value"
        });
        return;
    }

    Todo.getAll(sortOrder, (err, data) => {
        if (err) {
            // log.error('API', `Error Code: 500, Error retrieving todos.`);
            res.status(500).send({
                message:
                    err.message || "Some error occurred while retrieving records."
            });
        }
        else {
            res.status(200).send(data);
        }
    });
};

// Find a single Todos with a id
exports.findOne = (req, res) => {
    Todo.findById(req.params.id, (err, data) => {
        if (err) {
            if (err.kind === "not_found") {
                //  log.error('API', `Error Code: 404, Not found todos with id ${req.params.id}.`);
                res.status(404).send({
                    message: `Not found todos with id ${req.params.id}.`
                });
            } else {
                // log.error('API', `Error Code: 500, Error retrieving todos with id ${req.params.id}.`);
                res.status(500).send({
                    message: "Error retrieving todos with id " + req.params.id
                });
            }
        } else {
            res.status(200).send(data);
        }
    });
};


// Update a Todos identified by the id in the request
exports.update = (req, res) => {
    // Validate Request
    if (!req.body) {
        //log.error('API', `Error Code: 400, Content can not be empty!`);
        res.status(400).send({
            message: "Content can not be empty!"
        });
    }
    if (!validateData(req.body).valid) {
        //  log.error('API', `Error Code: 400, Content is not valide!`);
        return res.status(400).json({ error: validation.message });
    }

    console.log(req.body);

    Todo.updateById(
        req.params.id,
        new Todo({
            task: req.body.task,
            dueDate: moment(req.body.dueDate).format(dateFormat),
            isDone: req.body.isDone
        }),
        (err, data) => {
            if (err) {
                if (err.kind === "not_found") {
                    //    log.error('API', `Error Code: 404, Not found todos with id ${req.params.id}.`);
                    res.status(404).send({
                        message: `Not found todos with id ${req.params.id}.`
                    });
                } else {
                    //   log.error('API', `Error Code: 500, Error updating todos with id ${req.params.id}.`);
                    res.status(500).send({
                        message: "Error updating todos with id " + req.params.id
                    });
                }
            } else {
                res.status(200).json(true);
            }
        }
    );
};

// Delete a Todos with the specified id in the request
exports.delete = (req, res) => {
    Todo.remove(req.params.id, (err, data) => {
        if (err) {
            if (err.kind === "not_found") {
                // log.error('API', `Error Code: 404, Todo with id ${req.params.id} not found`);
                res.status(404).send({
                    message: `Not found todos with id ${req.params.id}.`
                });
            } else {
                // log.error('API', `Error Code: 500, Todo with id ${req.params.id} couldn't delete`);
                res.status(500).send({
                    message: "Could not delete todos with id " + req.params.id
                });
            }
        } else {
            res.status(200).json(true);
        };
    });
};
