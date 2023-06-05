const db = require("./db.js");

// constructor
const Todo = function (todo) {
    this.task = todo.task;
    this.dueDate = todo.dueDate;
    this.isDone = todo.isDone;
};

//create a todo
Todo.create = (newTodo, result) => {
    db.query("INSERT INTO todos SET ?", newTodo, (err, res) => {
        if (err) {
            console.log("error: ", err);
            result(err, null);
            return;
        }
        console.log("created todos: ", { id: res.insertId, ...newTodo });
        result(null, { id: res.insertId, ...newTodo });
    });
};

//return one todo by id
Todo.findById = (id, result) => {
    db.query(`SELECT * FROM todos WHERE id = ${id}`, (err, res) => {
        if (err) {
            console.log("error: ", err);
            result(err, null);
            return;
        }

        if (res.length) {
            console.log("found todos: ", res[0]);
            result(null, res[0]);
            return;
        }

        // not found Todos with the id
        result({ kind: "not_found" }, null);
    });
};

// return all todo[serach by task and return all if any]
Todo.getAll = (sortOrder, result) => {
    // var sql = "SELECT * FROM todos ORDER BY ??";
    // var inserts = [sortOrder];
    // var query = db.format(sql, inserts);

    var query = db.format("SELECT * FROM todos ORDER BY ??", [sortOrder]); // ?? represent a name of a field, ? represent a value
    // console.log(query);
    console.log("orderBy: " + sortOrder);
    db.query(query, [], (err, res) => {
        if (err) {
            console.log("error: ", err);
            result(null, err);
            return;
        }
        console.log("Todos: ", res);
        result(null, res);
    });
};

// Todo.getAllPublished = result => {
//     sql.query("SELECT * FROM Todos WHERE published=true", (err, res) => {
//         if (err) {
//             console.log("error: ", err);
//             result(null, err);
//             return;
//         }

//         console.log("Todos: ", res);
//         result(null, res);
//     });
// };

//update a todo
Todo.updateById = (id, todo, result) => {
    db.query(
        "UPDATE todos SET task = ?, dueDate = ?, isDone = ? WHERE id = ?",
        [todo.task, todo.dueDate, todo.isDone, id],
        (err, res) => {
            if (err) {
                console.log("error: ", err);
                result(null, err);
                return;
            }

            if (res.affectedRows == 0) {
                // not found Todos with the id
                result({ kind: "not_found" }, null);
                return;
            }

            console.log("updated Todos: ", { id: id, ...todo });
            result(null, { id: id, ...todo });
        }
    );
};

//delete a todo
Todo.remove = (id, result) => {
    db.query("DELETE FROM Todos WHERE id = ?", id, (err, res) => {
        if (err) {
            console.log("error: ", err);
            result(null, err);
            return;
        }

        if (res.affectedRows == 0) {
            // not found Todos with the id
            result({ kind: "not_found" }, null);
            return;
        }

        console.log("deleted Todos with id: ", id);
        result(null, res);
    });
};

// Todo.removeAll = result => {
//     sql.query("DELETE FROM Todos", (err, res) => {
//         if (err) {
//             console.log("error: ", err);
//             result(null, err);
//             return;
//         }

//         console.log(`deleted ${res.affectedRows} Todos`);
//         result(null, res);
//     });
// };

module.exports = Todo;