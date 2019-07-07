import React,{Component} from 'react';
import './QuizBar.css';
import {QuestionComponent} from './QuizQuestions';
import {AnswerComponent} from './QuizQuestions';


 const  data1 = {
    ID : 0, img : null,type : "QuestionResponse" , question: "Who was President during the Bay of Pigs fiasco?", correct : "meravici", point : 20
 };
 const data2 = {
    ID : 1, img : null,type : "MultipleChoice" , question: "romelia shvala titi?", answers : ["isi","egi","esi","igi"], correct : "egi", point : 30
 };
 const data3 = {
    ID : 2, img : null,type : "MultipleChoice" , question: "ra sargebloba moaqvs mamals?", answers : ["qatami gvadzlevs kvercxs","qatami gvadzlevs xorcs","qatami gvadzlevs bundghas","chinchlavs"], correct : "chinchlavs", point : 200
 };
 const data4 = {
    ID : 3, img : "./multiple-choice.jpg",type : "PictureResponseQuestions" , question: "ra xatia aqa dedi?", answers : null, correct : "kombosto shvilo",point : 20
 };
 const data5 = {
    ID : 4, img : null,type : "FillInTheBlank" , question: "rac mogiva ____ igideve tavidan.", answers : null, correct : "davita",point : 20
 };
 const questions = [data1,data2,data3,data4,data5]

 class QuizBar extends Component{
    constructor(props){
        super();
        if(props.Quiz.layout === "vertical"){
        this.state = {
        quizChecked : false,
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
        
    }
    render() {
        let summaryBar;
        let introBar;
        let verticalQuizBar;
        let horizontalQuizBar;
    
        if(this.props.Quiz.layout == "vertical"){
        if(!this.state.quizSet){
            var newAnswerTypes = this.state.answerTypes;
            for (let index = 0; index < questions.length - 1; index++) {
                newAnswerTypes.push("notanswered");
            }
            this.setState({answerTypes : newAnswerTypes ,quizSet : true});
        }
        }
        introBar = (
            <div className = "quizIntroBar">
                <a href = "www.fa" className = "introref" style = {{marginLeft : "20px"}}>categories</a><span style = {{paddingLeft : "5px",fontSize : "150%",color : "blue"}}>&#187;</span>
                <a href = "www.fa" className = "introref">{this.props.Quiz.categoryID}</a><span style = {{paddingLeft : "5px",fontSize : "150%",color : "blue"}}>&#187;</span>
                <a href = "www.fa" className = "introref">{this.props.Quiz.title}</a>
                <p style = {{fontSize : "150%",textAlign : "center",fontWeight : 800}}>{this.props.Quiz.title}</p>
                <p className = "introText">Following one is created by <button className = "introUser">{this.props.Quiz.authorID}</button> date : {this.props.Quiz.dateCreated}</p>
                <p className = "introText">Quiz category : {this.props.Quiz.categoryID}</p>
                <p className = "introText">Max point you can get is : {this.props.Quiz.numPoints}</p>
                <p className = "introText">Total questions : {questions.length}</p>
                <div className= "introText">Desctiption: <div className = "introDescription">{this.props.Quiz.description}</div></div>
                <div style = {{marginRight: "20px",marginBottom : "20px",float : "right"}}> <img  onClick = {()=>this.startQuiz(this)} className = "quizStart" style = {{width : "100px"}} src = {require("./quizStart.png")}  onClick = {()=>this.startQuiz(this)}></img></div>
            </div>
        );
        if(this.props.Quiz.layout == "vertical"){
            verticalQuizBar = (
            <div className = "verticalQuizBar">
                <ol>
                    {questions.map((question,index)=> <div><li>{QuestionComponent(question)}</li>
                    <div className = "QuizContent">{this.state.answerTypes[index]=="false" ?
                     <div><img style = {{top : "20px",position : "relative",width : "60px"}} src = {require("./wrong.png")}></img>Correct : {questions[index].correct}</div> 
                     : (this.state.answerTypes[index] == "true" ? 
                     <div><img style = {{top : "20px",position : "relative",width : "60px"}} src = {require("./correct.png")}></img>Correct!</div> 
                     : null)}</div>
                    </div>)}
                </ol>
                <button className = "introStart" onClick = {()=>this.submitQuiz(this)}>Submit</button>
                <button disabled = {this.state.quizChecked ? true : false} className = "introStart" onClick = {()=>this.checkQuiz(this)}>Check</button>
            </div>
        );
        }else{
            horizontalQuizBar = (
            <div className = "horizontalQuizBar" >
                    <div className = "questionTag">Question {this.state.questionPosition + 1}</div>
                    {QuestionComponent(questions[this.state.questionPosition])}
                    <div className = "QuizContent">{this.state.answerType=="false" ?
                     <div><img style = {{top : "20px",position : "relative",width : "60px"}} src = {require("./wrong.png")}></img>Correct : {questions[this.state.questionPosition].correct}</div> 
                     : (this.state.answerType == "true" ? 
                     <div><img style = {{top : "20px",position : "relative",width : "60px"}} src = {require("./correct.png")}></img>Correct!</div> 
                     : null)}</div>
                    <button className = "questionNext" onClick = {()=>this.nextQuestion(this,questions[this.state.questionPosition])}>{this.state.quizSubmit ? "Submit" : "Next"}</button>
                    <button disabled = {this.state.questionAnswered ? true : false} className = "questionCheck" onClick = {()=>this.checkQuestion(this,questions[this.state.questionPosition])}>Check</button>
            </div>
        ); 
        }
        
        summaryBar = (
            <div className = "horizontalQuizBar">
                    <h1 className = "introText">You completed quiz : {this.props.Quiz.title}</h1>
                    <h3 className = "introText"> Correct answers : {this.state.rightQuestions} / {questions.length}</h3>
                    <h3 className = "introText"> You collected : {this.state.points} points </h3>
                    <button className = "questionNext" onClick = {()=>this.returnToQuiz(this)}>return</button>
            </div>
        );
        
        return(
            
            (this.state.quizStarted ? (this.props.Quiz.layout == "vertical" ? verticalQuizBar : horizontalQuizBar) : (this.state.quizSummary ? summaryBar : introBar))
        
        );
    }
     returnToQuiz(page){
        page.setState({
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
        var answer = AnswerComponent(question,page.state.questionPosition);
        if(answer){
             page.setState({
            questionChecked : true,
            questionAnswered : true ,
            rightQuestions : num+1,
            points : pt+question.point,
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
        
        var openS = page.state.sideBarOpen;
        var openF = page.state.friendsBarOpen;
        page.setState({
            sideBarOpen : true,
            quizStarted : true,
            friendsBarOpen :true,
        });
    }
     nextQuestion(page,question){
        var pos = page.state.questionPosition;
        var answer = AnswerComponent(question,page.state.questionPosition);
        var correctQuestions = page.state.rightQuestions;
        var num = 0;
        var pt = page.state.points;
        if(!page.state.questionChecked){
            if(answer){
                num = 1;
                pt = page.state.points + question.point;
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
     checkQuiz(page){
        var num = 0;
        var pt = 0;
       for (let index = 0; index < questions.length; index++) {
        var answer = AnswerComponent(questions[index],index);
            if(answer){
                num = num+1;
                pt = pt + questions[index].point;
                page.state.answerTypes[index] = "true";
            }else{
                page.state.answerTypes[index] = "false";
            }
       }
       page.setState({
            points : pt,
            rightQuestions : num,
            quizChecked : true,
       });
    }
     submitQuiz(page){
        var num = 0;
        var pt = 0;
       for (let index = 0; index < questions.length; index++) {
        var answer = AnswerComponent(questions[index],index);
            if(answer){
                num = num+1;
                pt = pt + questions[index].point;
            }
       }
       page.setState({
            points : pt,
            quizChecked : false,
            rightQuestions : num,
            quizStarted : false,
            quizSummary : true,
       });
    }
}

export default QuizBar