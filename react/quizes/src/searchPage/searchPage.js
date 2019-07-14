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
        console.log("componentDidMount");
        var query = this.props.match.params.searchQuery
        this.fetchSearchQuery(query);
    }
    componentDidUpdate() {
        
    }

    fetchSearchQuery(query) {
        fetch("http://localhost:8080/ServletSearch?query="+query)
            .then(response => {

            if (response.ok) {
                console.log("ok status");
                return response.json();
            }
            else if (response.status === 401) {
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
        var accounts = this.state.accounts;
        var people = [accounts.length];
        var quizzes = this.state.quizzes;
        for (var i = 0; i < accounts.length; i++) {
            people[i] = searchedAccount(accounts[i],this,i);
        }
        var quizes = [quizzes.length];
        for (var i = accounts.length; i < quizzes.length + accounts.length; i++) {
            quizes[i - accounts.length] = searchQuiz(quizzes[i - accounts.length], this, i);
        }
        if (this.state.quizzes.length === 0 && this.state.accounts.length!==0) {
            return (
                <div id="searchBox">
                    <h1 id="search-head">Search</h1>
                    <div class="wrap">
                        <div className="searchCategory">
                            <input type="text" id="searchInput" className="searchTerm" placeholder="What are you looking for?" />
                            <button type="submit" onClick={() => this.search()} className="searchButton">
                                Search
                            </button>
                        </div>
                    </div>
                    <label id="srchPeopleLabel">People</label>
                    <div id="peopleBox">
                        {people}
                    </div>
                </div>
            );
        } else if (this.state.accounts.length === 0 && this.state.quizzes.length !== 0 ) {
            return (
                <div id="searchBox">
                    <h1 id="search-head">Search</h1>
                    <div className="wrap">
                        <div className="searchCategory">
                            <input type="text" id="searchInput" className="searchTerm" placeholder="What are you looking for?" />
                            <button type="submit" onClick={()=>this.search()} className="searchButton">
                                Search
                            </button>
                        </div>
                    </div>
                    <div id="srch-box-2">
                        <label id="srchPeopleLabel">Quizes</label>
                        <div id="quizesBox">
                            {quizes}
                        </div>
                    </div>

                </div>
            );
        } else if (this.state.accounts.length === 0 && this.state.quizzes.length === 0) {
            return (
                <div id="searchBox">
                    <h1 id="search-head">Search</h1>
                    <div className="wrap">
                        <div className="searchCategory">
                            <input type="text" id="searchInput" className="searchTerm" placeholder="What are you looking for?" />
                            <button type="submit" onClick={()=>this.search()} className="searchButton">
                                Search
                            </button>
                        </div>
                    </div>
                </div>
            );
        } else {
            return (
                <div id="searchBox">
                    <h1 id="search-head">Search</h1>
                    <div className="wrap">
                        <div className="searchCategory">
                            <input type="text" id="searchInput" className="searchTerm" placeholder="What are you looking for?" />
                            <button type="submit" onClick={() => this.search()} className="searchButton">
                                Search
                            </button>
                        </div>
                    </div>
                    <label id="srchPeopleLabel">People</label>
                    <div id="peopleBox">
                        {people}
                    </div>
                    <div id="srch-box-2">
                        <label id="srchPeopleLabel">Quizes</label>
                        <div id="quizesBox">
                            {quizes}
                        </div>
                    </div>

                </div>
            );
        }

                  
        
    }

    search() {
        var txt = document.getElementById("searchInput").value;
        this.props.history.push("/search/" + txt);
        this.fetchSearchQuery(txt);
        document.getElementById("searchInput").value = "";
    }

    
}
export default withRouter(searchPage);