import React,{Component} from 'react';
import '../css/QuizBar.css';
import {QuestionComponent} from './QuizQuestions';
import {AnswerComponent} from './QuizQuestions';


 class QuizBar extends Component{
    constructor(props){
        super(props);
        if(this.props.Quiz.layout === "vertical"){
        this.state = {
        startTime : 0,
        answersSet : false,
        quizChecked : false,
        quizSet : false,
        points : 0,
        rightQuestions : 0,
        answerTypes : ["notanswered"],
        realAnswers : [],
        answers : [],
        quizSummary : false,
        quizIntroOpen : true,
        sideBarOpen : true,
        friendsBarOpen : true,
        quizStarted : false,
        }
        }else{
            this.state = {
                startTime : 0,
                answersSet : false,
                points : 0,
                questionPosition : 0,
                realAnswers : [],
                answers : [],
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
        
    }
    render() {
        let summaryBar;
        let introBar;
        let verticalQuizBar;
        let horizontalQuizBar;
    
        if(this.props.Quiz.layout == "vertical"){
        if(!this.state.quizSet){
                var newAnswerTypes = this.state.answerTypes;
            for (let index = 0; index < this.props.Quiz.questions.length - 1; index++) {
                newAnswerTypes.push("notanswered");
            }
            this.setState({answerTypes : newAnswerTypes ,quizSet : true});
            }
        }
        if(!this.state.answersSet){
            var newAnswers = this.state.answers;
        for (let index = 0; index < this.props.Quiz.questions.length; index++) {
            newAnswers.push("Not Answered");
        }
        console.log(newAnswers);
        this.setState({answers : newAnswers ,answersSet : true});
        }
        introBar = (
            <div className = "quizIntroBar">
                <a href = "www.fa" className = "introref" style = {{marginLeft : "20px"}}>categories</a><span style = {{paddingLeft : "5px",fontSize : "150%",color : "blue"}}>&#187;</span>
                <a href = "www.fa" className = "introref">{this.props.Quiz.category}</a><span style = {{paddingLeft : "5px",fontSize : "150%",color : "blue"}}>&#187;</span>
                <a href = "www.fa" className = "introref">{this.props.Quiz.title}</a>
                <p style = {{fontSize : "150%",textAlign : "center",fontWeight : 800}}>{this.props.Quiz.title}</p>
                <p className = "introText">Following one is created by <button className = "introUser">{this.props.Quiz.author}</button> date : {this.props.Quiz.dateCreated}</p>
                <p className = "introText">Quiz category : {this.props.Quiz.category}</p>
                <p className = "introText">Max point you can get is : {this.props.Quiz.numPoints}</p>
                <p className = "introText">Total questions : {this.props.Quiz.questions.length}</p>
                <div className= "introText">Desctiption: <div className = "introDescription">{this.props.Quiz.description}</div></div>
                <div style = {{marginRight: "20px",marginBottom : "20px",float : "right"}}> <img  onClick = {()=>this.startQuiz(this)} className = "quizStart" style = {{width : "100px"}} src = {require("./quizStart.png")}  onClick = {()=>this.startQuiz(this)}></img></div>
            </div>
        );
        if(this.props.Quiz.layout == "vertical"){
            verticalQuizBar = (
            <div className = "verticalQuizBar">
                <div style = {{marginTop : "15px" ,fontSize : "150%",textAlign : "center",fontWeight : 800}}>{this.props.Quiz.title}</div>
                <ol>
                    {this.props.Quiz.questions.map((question,index)=> <div><li>{QuestionComponent(question,index)}</li>
                    <div className = "QuizContent">{this.state.answerTypes[index]=="false" ?
                     <div><img style = {{top : "20px",position : "relative",width : "60px"}} src = {require("./wrong.png")}></img>Incorrect!</div> 
                     : (this.state.answerTypes[index] == "true" ? 
                     <div><img style = {{top : "20px",position : "relative",width : "60px"}} src = {require("./correct.png")}></img>Correct!</div> 
                     : null)}</div>
                    </div>)}
                </ol>
                <button className = "introStart" onClick = {()=>this.submitQuiz()}>Submit</button>
                {this.state.quizChecked ? null : <button className = "introStart" onClick = {()=>this.checkQuiz()}>Check</button>}
            </div>
        );
        }else{
            horizontalQuizBar = (
            <div className = "horizontalQuizBar" >
                    <div className = "questionTag">Question {this.state.questionPosition + 1}</div>
                    {QuestionComponent(this.props.Quiz.questions[this.state.questionPosition],this.state.questionPosition)}
                    <div className = "QuizContent">{this.state.answerType=="false" ?
                     <div><img style = {{top : "20px",position : "relative",width : "60px"}} src = {require("./wrong.png")}></img>Incorrect!</div> 
                     : (this.state.answerType == "true" ? 
                     <div><img style = {{top : "20px",position : "relative",width : "60px"}} src = {require("./correct.png")}></img>Correct!</div> 
                     : null)}</div>
                    <button className = "questionNext" onClick = {()=>this.nextQuestion(this,this.props.Quiz.questions[this.state.questionPosition])}>{this.state.quizSubmit ? "Submit" : "Next"}</button>
                    <button disabled = {this.state.questionAnswered ? true : false} className = "questionCheck" onClick = {()=>this.checkQuestion(this,this.props.Quiz.questions[this.state.questionPosition])}>Check</button>
            </div>
        ); 
        }
        
        summaryBar = (
            <div className = "horizontalQuizBar">
                    <h1 className = "introText">You completed quiz : {this.props.Quiz.title}</h1>
                    <h3 className = "introText"> Correct answers : {this.state.rightQuestions} / {this.props.Quiz.questions.length}</h3>
                    <h3 className = "introText"> You collected : {this.state.points} points </h3>
                    <h2 className = "introText">Answers</h2>
                    <h3 className = "introText">{this.state.answers.map((type,index) => <div>{(index+1)+".  "+type+" - "}{this.state.realAnswers[index]}</div>)}</h3>
                    <button className = "questionNext" onClick = {()=>this.returnToQuiz(this)}>return</button>
            </div>
        );
        
        return(
            
            (this.state.quizStarted ? (this.props.Quiz.layout == "vertical" ? verticalQuizBar : horizontalQuizBar) : (this.state.quizSummary ? summaryBar : introBar))
        
        );
    }
     returnToQuiz(page){
        page.setState({
            startTime : 0,
            questionPosition : 0,
            rightQuestions : 0,
            answerType : "notanswered",
            questionAnswered : false,
            questionChecked : false,
            quizSubmit : false,
            quizSummary : false,
            quizIntroOpen : true,
            friendsBarOpen : true,
            quizStarted : false,
            answerTypes : ["notanswered"],
            quizSet : false
        })
    }
     checkQuestion(page,question){
        var num = page.state.rightQuestions;
        var pt = page.state.points;
        var answer = AnswerComponent(question,page.state.questionPosition,this);
        if(answer){
             page.setState({
            questionChecked : true,
            questionAnswered : true ,
            rightQuestions : num+1,
            points : pt+question.num_point,
            answerType : "true",
             });
        }else{
             page.setState({
             questionChecked : true,
             questionAnswered : true ,
             answerType : "false"
             });
                 
        }
       
    }
     startQuiz(page){
        
    
        page.setState({
            quizStarted : true,
            startTime : Date.now(),
        });
    }
     nextQuestion(page,question){
        var questions = this.props.Quiz.questions;
        var pos = page.state.questionPosition;
        var answer = AnswerComponent(question,page.state.questionPosition,this);
        var correctQuestions = page.state.rightQuestions;
        var num = 0;
        var pt = page.state.points;
        if(!page.state.questionChecked){
            if(answer){
                num = 1;
                pt = page.state.points + question.num_point;
            }
        }
            if(pos < questions.length - 1){
               page.setState({
                points : pt,
                questionChecked : false,
                questionPosition : pos + 1,
                rightQuestions : correctQuestions+num,
                questionAnswered : false,
                answerType : "notanswered"
               })
            }else if(pos == questions.length - 1){
                page.setState({
                 points : pt,
                 questionChecked : false,
                 quizSubmit : true,
                 rightQuestions : correctQuestions+num,
                 questionPosition : 0,
                 questionAnswered : false,
                 answerType : "notanswered",
                 quizStarted : false,
                 quizSummary : true,
              })
             }
    }
     checkQuiz(){
        var num = 0;
        var pt = 0;
        var questions = this.props.Quiz.questions;
       for (let index = 0; index < questions.length; index++) {
        var answer = AnswerComponent(questions[index],index,this);
            if(answer){
                num = num+1;
                pt = pt + questions[index].num_point;
                this.state.answerTypes[index] = "true";
            }else{
                this.state.answerTypes[index] = "false";
            }
       }
       this.setState({
            points : pt,
            rightQuestions : num,
            quizChecked : true,
       });
    }
     submitQuiz(){
        var num = 0;
        var pt = 0;
        var questions = this.props.Quiz.questions;
       
       for (let index = 0; index < questions.length; index++) {
        var answer = AnswerComponent(questions[index],index,this);
            if(answer){
                num = num+1;
                pt = pt + questions[index].num_point;
                this.state.answerTypes[index] = "true";
            }else{
                this.state.answerTypes[index] = "false";
            }
       }
    
    
    
       this.setState({
            points : pt,
            quizChecked : false,
            rightQuestions : num,
            quizStarted : false,
            quizSummary : true,
       });
       this.sendResults();
    }

    sendResults() {
        var results = {
          "personID" : this.props.personID,
          "quizID" : this.props.Quiz.quizID,
          "points" : this.state.points,
          "timeSpent" : (Date.now()-this.state.startTime),
          "dateSubmittedMillis" : Date.now(),
        }
        fetch("http://localhost:8080/ServletSaveQuizResults", {
            method: "POST", 
            body: JSON.stringify(results),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(response => {
              if (response.status !== 200 ) {
                  console.log("erorinio");
              }
            }).catch(error => {
                    console.log(error);
            });
      }
}

export default QuizBar