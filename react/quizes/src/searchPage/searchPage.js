import React, { Component } from 'react';
import { withRouter } from 'react-router-dom'
import { searchedAccount } from './searchedAccount';
import "./searchPage.css"
import { searchQuiz } from './searchQuiz';
class searchPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            query: "",
            accounts: [],
            quizzes:[],
        }
    }
    componentDidMount() {
        var query = this.props.match.params.searchQuery
        this.setState({ query: query })
        this.fetchSearchQuery(query);
    }

    fetchSearchQuery(query) {
        fetch("http://localhost:8080/ServletSearch?query="+query)
            .then(response => {

            if (response.ok) {
                console.log("ok status");
                return response.json();
            }
            else if (response.status == 401) {
                return null;
            } else {
                return null;
            }
        }).then(response => this.processResponse(response))
            .catch(error => {
                console.log(error);
            });
    }
    processResponse(response) {
        console.log("in proccess response");
        var accounts = response.accounts;
        var quizzes = response.quizzes;
        console.log(accounts.length);
        console.log(quizzes);
        this.setState({
            accounts: accounts,
            quizzes: quizzes
        });
    }
    render() {
        /*
        var data = {
            username: "jikssi",
            firstname: "giorgi",
            secondname: "jikia"
        }
        var people = [10];
        for (var i = 0; i < 2; i++) {
            people[i] = searchedAccount(data, this);
        }
        */
       
        
        var accounts = this.state.accounts;
        var people = [accounts.length];
        var quizzes = this.state.quizzes;
        for (var i = 0; i < accounts.length; i++) {
            people[i] = searchedAccount(accounts[i],this);
        }
        var quizes = [quizzes.length];
        for (var i = 0; i < quizzes.length; i++) {
            quizes[i] = searchQuiz(quizzes[i], this);
        }
        return (
            <div id="searchBox">
                <label id="srchPeopleLabel">People</label>
                <div id="peopleBox">
                    {people}
                </div>
                <label id="srchPeopleLabel">Quizes</label>
                <div>
                    {quizes}
                </div>
            </div>
        );
    }

    
}
export default withRouter(searchPage);