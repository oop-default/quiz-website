import React, { Component } from 'react';
import '../css/Login.css';
import { withRouter } from 'react-router-dom'
import { getJWT } from '../../Jwt';
class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {
            email: "",
            password: ""
        }
        this.change = this.change.bind(this);
        this.submit = this.submit.bind(this);
    }
    componentDidMount() {
        const jwt = getJWT();
        if (jwt) {
            this.props.history.push("/");
        }
    }

    componentDidUpdate(prevProps, prevState) {
        console.log("componentDidUpdate");

    }

    change(e) {
        this.setState({
            [e.target.name]: e.target.value
        })
    }
    submit(e) {
        e.preventDefault();
        console.log("submmit");
        var data = {
            "username": this.state.email,
            "password": this.state.password
        }
        var error = document.getElementById("errorMessage");
        if (data.password==="" || data.username==="") {
            error.innerHTML = "Incorrect input!";
        } else {
            this.postData(data);
        }
        
    }
    postData(data) {
        var url = 'http://localhost:8080/ServletLogin';
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
        var error = document.getElementById("errorMessage")
        if (data.status === 200) {
            var jwtToken = data.jwsToken;
            console.log(jwtToken);
            localStorage.setItem("cool-jwt", jwtToken)
            this.props.history.push("/");
        } else if (data.status == 400) {
            error.innerHTML = data.message;
        }
    }
    render() {
        return (
            <div>
                <div id="errorMessage"></div>
                <table id="LoginTable" cellSpacing="0">
                    <tbody>
                        <tr>
                            <td>
                                <label id="usernameLabel">Username or mail</label>
                            </td>
                            <td>
                                <label id="passwordLabel">Password</label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input id="usernameInput" type="text" name="email" onChange={e => this.change(e)} value={this.state.email} />
                            </td>
                            <td >
                                <input id="passwordInput" type="password" name="password" onChange={e => this.change(e)} value={this.state.password} />
                            </td>
                            <td>
                                <input id="submitInput" type="submit" value="Log in" onClick={e => this.submit(e)} />
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        );
    }
}
export default withRouter(Login);