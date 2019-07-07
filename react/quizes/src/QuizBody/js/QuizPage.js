import React, { Component } from 'react';
import QuizBar from './QuizBar';
import SideBar from './SideBar'
import NavBar from './NavBar'
import Friends from './Friends'
import './QuizPage.css';
// const  todoItems = [
//   { id: 1, title: "task 1", done: false },
//   { id: 2, title: "task 2", done: false },
//   { id: 3, title: "task 3", done: true },
//   { id: 4, title: "task 4", done: false }
// ];
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
    
    if(Quiz.layout == "vertical"){
      this.state = {
        Quiz : Quiz,
        quizSet : false,
        points : 0,
        rightQuestions : 0,
        answerTypes : ["notanswered"],
        quizSummary : false,
        quizIntroOpen : true,
        sideBarOpen : true,
        friendsBarOpen : true,
        quizStarted : false,
      }
    }else{
      this.state = {
        Quiz : Quiz ,
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
    }
    // this.state = {todos:todoItems};
  }
  render() {
    return(
       
       <body className = "bodyStyle">
        <NavBar></NavBar>
        <SideBar ></SideBar>
       {/* {SideBar(topScores,friendTopScores,previousTries,this) } */}
       {/* {QuizBar(Quiz,this) } */}
       <QuizBar Quiz = {Quiz}></QuizBar>
       <Friends></Friends>
       </body>
    );
  }
}

export default QuizPage