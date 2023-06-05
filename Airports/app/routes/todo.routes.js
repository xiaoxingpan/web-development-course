module.exports = app => {
    const todo = require("../controllers/todo.controller.js");

    var router = require("express").Router();

    // Create a new todos
    router.post("/", todo.create);

    // Retrieve all todos
    router.get("/", todo.findAll);

    // Retrieve a single ToDos with id
    router.get("/:id([0-9]+)", todo.findOne);

    // Update a ToDo with id
    router.put("/:id([0-9]+)", todo.update);

    // Delete a ToDo with id
    router.delete("/:id([0-9]+)", todo.delete);

    // Define the prefix
    app.use('/api/todos', router);
};