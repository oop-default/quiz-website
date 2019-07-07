import React, { Component } from 'react';
import QuizBar from './QuizBar';
import SideBar from './SideBar'
import NavBar from './NavBar';
import cookie from 'react-cookies'
import '../css/QuizPage.css';
import FriendsBar from './FriendsBar';


// const  todoItems = [
//   { id: 1, title: "task 1", done: false },
//   { id: 2, title: "task 2", done: false },
//   { id: 3, title: "task 3", done: true },
//   { id: 4, title: "task 4", done: false }
// ];
 
const  topScores = [
  { pos: 1, user: "vvash17", score: 1000 },
  { pos: 2, user: "tbarb18", score: 900 },
  { pos: 3, user: "balsh17", score: 800 },
  { pos: 4, user: "dgerm14", score: 700 }
];
const  friendTopScores = [
  { pos: 1, user: "vvash17", score: 1000 },
  { pos: 2, user: "tbarb18", score: 900 },
  { pos: 3, user: "balsh17", score: 800 },
  { pos: 4, user: "dgerm14", score: 700 }
];
const previousTries = [
  { pos: 1, score: 1200.2 },
  { pos: 2, score: 812 },
  { pos: 3, score: 756 },
  { pos: 4, score: 700 }
];
const  Quiz = {
    ID: 1,
    title: "ragac quizi",
    author: "vvash17", 
    description : "ragaca ragaca magari quizia ragaca ragaca ragaca ragaca magari quizia ragaca ragaca ragaca ragaca magari quizia ragaca ragaca",
    dateCreated : "2019 saswavlo wlis bolo",
    type: "ragac kategoria",
    numPoints: 270,
    layout : "horizontal"
};
class QuizPage extends Component {

  constructor(props) {
    super(props);
      console.log("quizPage");
      this.fetchQuizData = this.fetchQuizData.bind(this);
      this.processData = this.processData.bind(this);
     // this.processResponse = this.processResponse(this);
      this.state = {
         
          quiz: {},
          points: 0,
          questionPosition: 0,
          rightQuestions: 0,
          answerType: "notanswered",
          questionAnswered: false,
          questionChecked: false,
          quizSubmit: false,
          quizSummary: false,
          quizIntroOpen: true,
          sideBarOpen: true,
          friendsBarOpen: true,
          quizStarted: false,
          notAuthenticated: undefined
      };
    // this.state = {todos:todoItems};
    }
     componentDidMount() {
        console.log(this.props.match.params.quizId);
        var quizId = this.props.match.params.quizId;
        this.fetchQuizData(quizId);
    }
    fetchQuizData(quizId) {
        var token = cookie.load('jwt');
        var bearer = 'Bearer ' + token;
        console.log(bearer);
        var url = "http://localhost:8080/ServletQuiz?id=" + quizId;
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

        })  .then(response => this.processResponse(response))
            .catch(error => {
                console.log(error);  
            });
    }


     processResponse(data) {
        if (data != null) {
            var jsonData = JSON.stringify(data)
            console.log(jsonData);
            this.processData(jsonData);
        } else if (this.state.notAuthenticated) {
            console.log("not Authenticated");
        } else {
            this.props.history.push("/notfound");
        } 
        
    }

    processData(jsonString) {
        var object = JSON.parse(jsonString);
        var newQuiz = {
            author: object.author,
            title: object.title,
            description: object.description,
            category: object.type,
            numPoints: object.num_points,
            dateCreated: object.dateCreated,
            questions: object.questions
        }
        this.setState({ quiz: newQuiz });
        console.log(this.state.quiz);
    }
  render() {

      if (this.state.loading === true) {
          return (<div>Searching..</div>);
        } else {
            return (
                <body className="bodyStyle">

                    {NavBar()}
                    {SideBar(topScores, friendTopScores, previousTries, this)}
                    {QuizBar(this.state.quiz, this)}
                    {FriendsBar(this)}
                </body>
                
                );
        }
        
    
  }
}

export default QuizPage