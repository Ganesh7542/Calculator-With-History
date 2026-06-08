function appendValue(value){
    document.getElementById("display").value += value;
}

function clearDisplay(){
    document.getElementById("display").value = "";
}

function calculate(){

    let expression =
        document.getElementById("display").value;

    let result = eval(expression);

    document.getElementById("display").value =
        result;

    let li =
        document.createElement("li");

    li.innerText =
        expression + " = " + result;

    document.getElementById("history")
        .appendChild(li);

    fetch("save", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body:
            "expression=" +
            encodeURIComponent(expression) +
            "&result=" +
            encodeURIComponent(result)
    });
}