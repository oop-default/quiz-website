import React, { Component } from 'react';
import QuizBar from './QuizBar';
import SideBar from './SideBar'
import NavBar from '../../homepage/js/NavBar'
import Friends from './Friends'
import '../css/QuizPage.css';
import cookie from 'react-cookies';
// const  todoItems = [
//   { id: 1, title: "task 1", done: false },
//   { id: 2, title: "task 2", done: false },
//   { id: 3, title: "task 3", done: true },
//   { id: 4, title: "task 4", done: false }
// ];
// const  Quiz = {
//   ID : 1, title : "ragac quizi", authorID : "vvash17", 
//   description : "ragaca ragaca magari quizia ragaca ragaca ragaca ragaca magari quizia ragaca ragaca ragaca ragaca magari quizia ragaca ragaca",
//   dateCreated : "2019 saswavlo wlis bolo",
//   categoryID : "ragac kategoria",numPoints : 270,
//   layout : "vertical"
// };

class QuizPage extends Component {

  constructor(props) {
    super(props);

    // console.log("quizPage");
    //     this.fetchQuizData = this.fetchQuizData.bind(this);
    //     this.processData = this.processData.bind(this);


    console.log("constructor");
    
    this.state = {quiz : null}
    // this.state = {todos:todoItems};
  }
  componentDidMount() {
    // console.log(this.props.match.params.quizId);
    var quizId = this.props.match.params.quizId;
    this.fetchQuizData(quizId);
}
fetchQuizData(quizId) {
    var token = cookie.load('jwt');
    var bearer = 'Bearer ' + token;
    console.log(bearer);
    var url = "http://localhost:8030/ServletQuiz?id=" + quizId;
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

    })  .then(response => this.processResponse(response,quizId))
        .catch(error => {
            console.log(error);  
        });
}


 processResponse(data,quizId) {
    if (data != null) {
        var jsonData = JSON.stringify(data)
        console.log(jsonData);
        this.processData(jsonData,quizId);
    } else if (this.state.notAuthenticated) {
        console.log("not Authenticated");
    } else {
        this.props.history.push("/notfound");
    } 
    
}

processData(jsonString,quizId) {
    var object = JSON.parse(jsonString);
    var newQuiz = {
        quizID : quizId,
        author: object.author,
        title: object.title,
        description: object.description,
        category: object.type,
        numPoints: object.num_points,
        dateCreated: object.dateCreated,
        questions: object.questions,
        layout : "vertical"
    }
    this.setState({ quiz: newQuiz });
    console.log(this.state.quiz);
}
render() {

  if (this.state.quiz === null) {
      return (<div>Searching..</div>);
    } else {
        return (
               <body className = "bodyStyle">
                <NavBar></NavBar>
                <SideBar ></SideBar>
               {/* {SideBar(topScores,friendTopScores,previousTries,this) } */}
               {/* {QuizBar(Quiz,this) } */}
               <QuizBar personID = "1" Quiz = {this.state.quiz}></QuizBar>
               <Friends></Friends>
               </body>
        )
    


}
      }
}

export default QuizPage