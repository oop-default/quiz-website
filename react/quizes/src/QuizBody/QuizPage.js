import React, { Component } from 'react';
import QuizBar from './QuizBar';
import SideBar from './SideBar'
import NavBar from './NavBar'
import FriendsBar from './FriendsBar'
import './QuizPage.css';
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
   ID : 1, title : "ragac quizi", authorID : "vvash17", 
   description : "ragaca ragaca magari quizia ragaca ragaca ragaca ragaca magari quizia ragaca ragaca ragaca ragaca magari quizia ragaca ragaca",
   dateCreated : "2019 saswavlo wlis bolo",
   categoryID : "ragac kategoria",numPoints : 270,
   layout : "horizontal"
};
class QuizPage extends Component {

  constructor(props) {
    super(props);
    console.log("constructor");
    
    this.state = {
        points : 0,
        questionPosition : 0,
        rightQuestions : 0,
        answerType : "notanswered",
        questionAnswered : false,
        questionChecked : false,
        quizSubmit : false,
        quizSummary : false,
        quizIntroOpen : true,
        sideBarOpen : true,
        friendsBarOpen : true,
        quizStarted : false,
      }
    // this.state = {todos:todoItems};
  }
  render() {
    return(
       
       <body className = "bodyStyle">
       {NavBar()}
       {SideBar(topScores,friendTopScores,previousTries,this) }
       {QuizBar(Quiz,this) }
       {FriendsBar(this)}
       </body>
    );
  }
}

export default QuizPage