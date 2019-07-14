import React, { Component } from 'react';
import '../css/Register.css';
import cookie from 'react-cookies';

import { withRouter } from 'react-router-dom'
class Register extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username:"",
            firstname: "",
            secondname:"",
            gender:"Male",
            password: ""
        }
        this.change = this.change.bind(this);
        this.submit = this.submit.bind(this);
    }
    change(e) {
        this.setState({
            [e.target.name]: e.target.value
        })
    }
    submit(e) {
        e.preventDefault();
        var error = document.getElementById("ErrorMessageRegister");
        var data = {
            "username": this.state.username,
            "password": this.state.password,
            "firstname": this.state.firstname,
            "secondname": this.state.secondname,
            "gender": this.state.gender
        }
        if (!checkInputValidation(data.username, data.password, data.firstname, data.secondname, data.email)) {
            error.innerHTML = "Incorrect input!";
        } else {
            this.postData(data);
        }
       
    }
    postData(data) {
        var url = 'http://localhost:8080/ServletRegister';
        fetch(url, {
            method: 'POST', // or 'PUT'
            body: JSON.stringify(data), // data can be `string` or {object}!
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(res => res.json())
            .then(response => this.processResponse(response))
            .catch(error => console.error('Error:', error));
    }
    processResponse(data) {
        var error = document.getElementById("ErrorMessageRegister")
        if (data.status === 200) {
            var jwtToken = data.jwsToken;
            cookie.save('jwt', jwtToken, {
                path: '/',
                maxAge:1000*60*60
            })
            this.props.history.push("/");
        } else if (data.status == 406) {
            error.innerHTML = data.message;
        } else {
            error.innerHTML = data.message;
        }
    }
    render() {
        return (
            <div>
                <h1>Create a New Account</h1>
                <div id="LoginForm">
                    <div id="ErrorMessageRegister"></div>
                    <br></br>

                    <div>
                        <input id="firstnameInput" type="text" placeholder="First name" onClick={(event) => defaultConditions(event.target)}
                            onChange={e => this.change(e)} value={this.state.firstname} name="firstname" />
                        <input id="secondnameInput" type="text" placeholder="Second name" onClick={(event) => defaultConditions(event.target)}
                            onChange={e => this.change(e)} name="secondname" />
                    </div>
                    <label id="genderLabel">Gender</label>
                    <span className="genderBox">
                        <span>
                            <input type="radio" name="gender" value="Male" id="g1" defaultChecked onClick={e => this.change(e)} />
                            <label htmlFor="g1">Male</label>
                        </span>
                        <input type="radio" name="gender" value="Female" id="g2" onClick={e => this.change(e)} />
                        <label htmlFor="g2">Female</label>
                        <span>
                            <input type="radio" name="gender" value="Other" id="g3" onClick={e => this.change(e)} />
                            <label htmlFor="g3">Other</label>
                        </span>
                    </span>

                    <input id="registerUsernameInput" type="text" placeholder="Username" onClick={(event) => defaultConditions(event.target)}
                        onChange={e => this.change(e)} name="username" value={this.state.username} />

                    <input id="registerPasswordInput" type="password" placeholder="Password" onClick={(event) => defaultConditions(event.target)}
                        onChange={e => this.change(e)} name="password" value={this.state.password} />

                    <input id="registerSubmitInput" type="submit" value="Sign Up" onClick={e => this.submit(e)} />

                </div>
            </div>
        );
    }
}
function defaultConditions(input) {
    input.style.borderColor = "darkgrey";
    var error = document.getElementById("ErrorMessageRegister");
    error.innerHTML = "";
}
function checkInputValidation(username, password, firstname, secondname, email) {
    var valid = true;
    if (username === "") {
        document.getElementById("registerUsernameInput").style.borderColor = "red";
        valid = false;
    }
    if (password === "") {
        document.getElementById("registerPasswordInput").style.borderColor = "red";
        valid = false;
    }
    if (firstname === "") {
        document.getElementById("firstnameInput").style.borderColor = "red";
        valid = false;
    }
    if (secondname === "") {
        document.getElementById("secondnameInput").style.borderColor = "red";
        valid = false;
    }
   
    return valid;
}
export default withRouter(Register);