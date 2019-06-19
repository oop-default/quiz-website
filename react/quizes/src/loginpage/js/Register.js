import React, { Component } from 'react';
import Login from './Login';
import ReactDOM from 'react-dom';
import '../css/Register.css'
class Register extends Component {
    constructor() {
        super();
        console.log();
    }
    render() {
        return (
            <div>
                <h1>Create a New Account</h1>
                <div id="LoginForm">
                    <div id="ErrorMessageRegister"></div>
                    <br></br>

                    <div>
                        <input id="firstnameInput" type="text" placeholder="First name" onClick={(event) => defaultConditions(event.target)} />
                        <input id="secondnameInput" type="text" placeholder="Second name" onClick={(event) => defaultConditions(event.target)} />
                    </div>
                    <label id="genderLabel">Gender</label>
                    <span className="genderBox">
                        <span>
                            <input type="radio" name="sex" value="1" id="g1" defaultChecked />
                            <label htmlFor="g1">Male</label>
                        </span>
                        <input type="radio" name="sex" value="2" id="g2" />
                        <label htmlFor="g2">Female</label>
                        <span>
                            <input type="radio" name="sex" value="3" id="g3" />
                            <label htmlFor="g3">Other</label>
                        </span>
                    </span>

                    <input id="emailInput" type="text" placeholder="Email" onClick={(event) => defaultConditions(event.target)} />

                    <input id="registerUsernameInput" type="text" placeholder="Username" onClick={(event) => defaultConditions(event.target)} />

                    <input id="registerPasswordInput" type="password" placeholder="Password" onClick={(event) => defaultConditions(event.target)} />

                    <input id="registerSubmitInput" type="submit" value="Sign Up" onClick={checkRegistration} />

                </div>
            </div>
        );
    }
}
function loginApp() {
    ReactDOM.render(<Login />, document.getElementById('root'));
    console.log("in register");
}
function defaultConditions(input) {
    input.style.borderColor = "darkgrey";
    var error = document.getElementById("ErrorMessageRegister");
    error.innerHTML = "";
}
function isNotValid(inputValue) {
    if (inputValue.value === "") {
        inputValue.style.borderColor = "red";
        return true;
    }
    return false;
}
function checkInputValidation(username, password, firstname, secondname, email) {
    var valid = true;
    if (isNotValid(username)) valid = false;
    if (isNotValid(password)) valid = false;
    if (isNotValid(firstname)) valid = false;
    if (isNotValid(secondname)) valid = false;
    if (isNotValid(email)) valid = false;
    return valid;
}
function checkRegistration() {
    var username = document.getElementById("registerUsernameInput");
    var password = document.getElementById("registerPasswordInput");
    var firstname = document.getElementById("firstnameInput");
    var secondname = document.getElementById("secondnameInput");
    var email = document.getElementById("emailInput");
    var error = document.getElementById("ErrorMessageRegister");
    var maleCheckBox = document.getElementById("g1");
    var femaleCheckBox = document.getElementById("g2");
    var gender = "Other"
    if (maleCheckBox.checked) gender = "Male";
    if (femaleCheckBox.checked) gender = "Female";
    if (!checkInputValidation(username, password, firstname, secondname, email)) {
        error.innerHTML = "Incorrect input!";
    } else {
        console.log(username.value);
        console.log(password.value);
        console.log(firstname.value);
        console.log(secondname.value);
        console.log(email.value);
        console.log(gender);
        var data = {
            "username": username.value,
            "password": password.value,
            "firstname": firstname.value,
            "secondname": secondname.value,
            "email": email.value,
            "gender": gender
        }
        var url = 'http://localhost:8080/ProjectApi_war_exploded/ServletRegister';
        fetch(url, {
            method: 'POST', // or 'PUT'
            body: JSON.stringify(data), // data can be `string` or {object}!
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(res => res.json())
            .then(response => console.log('Success:', JSON.stringify(response)))
            .catch(error => console.error('Error:', error));
    }
}
export default Register;