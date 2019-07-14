import React, { Component } from 'react';
import { withRouter } from 'react-router-dom'
import cookie from 'react-cookies';
import CategoryComponent from "./QuizCategoryComponent"
import { SearchInput } from './SearchInput';

class CategoriesPage extends Component {
    constructor(props) {
        super(props);
        this.state={
			data:null
        }
    }

    componentDidMount() {
        var category = this.props.match.params.category;
        this.fetchQuizesByCategory(category);
    }

    fetchQuizesByCategory(category) {
        var token = cookie.load('jwt');
        var bearer = 'Bearer ' + token;
        console.log(bearer);
        var url = "http://localhost:8080/ServletQuizCategories?category=" + category;
        console.log(url);
        fetch(url, {
            headers: {
                'Authorization': bearer
            }
        }).then(response => {

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
    processResponse(data) {
        this.setState({ data: data })
        console.log(data);
    }
    render() {
        if (this.state.data === null) {
            return (<div>Searching...</div>);
        } else {
            var data = this.state.data;
            var quizzes = [data.length];
            for (var i = 0; i < data.length; i++) {
                quizzes[i] = CategoryComponent(data[i],this,i);
            }
            if (this.state.data.length === 0) {
                return (
                    <div>
                        <h1 id="quiz-category-header">Category not found</h1>
                        <div className="wrap">
                            <div className="searchCategory">
                                <input type="text" id="searchInput" className="searchTerm" placeholder="What are you looking for?" />
                                <button type="submit" onClick={() => this.search()} className="searchButton">
                                    Search
                            </button>c
                            </div>
                        </div>
                    </div>

                );
            } else {
                return (
                    <div>
                        <h1 id="quiz-category-header">{this.state.data[0].type}</h1>
                        <div className="wrap">
                            <div className="searchCategory">
                                <input type="text" id="searchInput" className="searchTerm" placeholder="What are you looking for?" />
                                <button type="submit" onClick={() => this.search()} className="searchButton">
                                    Search
                            </button>
                            </div>
                        </div>
                        <main>
                            <div className="album py-5 bg-light">
                                <div className="containerCategory">

                                    <div className="row">
                                        {quizzes}
                                    </div>
                                </div>
                            </div>
                        </main>
                    </div>

                );
            }
            
        }
    }

    search() {
        var category = document.getElementById("searchInput").value;
        this.props.history.push("/category/" + category);
        this.fetchQuizesByCategory(category);
        document.getElementById("searchInput").value = "";
    }

    paintQuizes(data) {

        return (<div></div>);
    }
}
export default withRouter(CategoriesPage)