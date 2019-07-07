import React from 'react';
import '../css/QuizBar.css';
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
    ID : 4, img : null,type : "MultiAnswerQuestions" , question: "ravaxar simon?", answers : null, correct : ["kargat","mtla kargat"],point : 20
 };
 const questions = [data1,data2,data3,data4,data5]

function QuizBar(Quiz,page){
    let summaryBar;
    let introBar;
    let verticalQuizBar;
    let horizontalQuizBar;



    introBar = (
        <div className="quizIntroBar">
            <h1 className="introText">{Quiz.title}</h1>
            <p className="introText">Following one is created by <button className="introUser" >{Quiz.author}</button> date : {Quiz.dateCreated}</p>
            <p className="introText">Quiz category : {Quiz.category}</p>
            <p className = "introText">Max point you can get is : {Quiz.numPoints}</p>
            <p className = "introText">Total questions : {questions.length}</p>
            <p className= "introText">Desctiption: <div className = "introDescription">{Quiz.description}</div></p>
            <button className = "introStart" onClick = {()=>startQuiz(page)}>Start</button>
        </div>
    );

    verticalQuizBar = (
        <div className = {page.state.sideBarOpen ? "reducedQuizBar" : "fullQuizBar"}>
            <ol>
                {questions.map((question)=> <li>{QuestionComponent(question)}</li>)}
            </ol>
            <button className = "introStart" onClick = {()=>nextQuestion(page)}>Submit</button>
        </div>
    );
    horizontalQuizBar = (
        <div className = {page.state.sideBarOpen ? "reducedQuizBar" : "fullQuizBar"} 
             style = {page.state.questionAnswered ? 
             (page.state.answerType == "true" ? {backgroundColor : "rgba(67, 221, 36, 0.2)"} : {backgroundColor : "rgba(194, 38, 46, 0.2)"} ) 
                : {backgroundColor : "rgba(21, 52, 187, 0.2)"}}>
                <div className = "questionTag">Question {page.state.questionPosition + 1}</div>
                {QuestionComponent(questions[page.state.questionPosition])}
                <button className = "questionNext" onClick = {()=>nextQuestion(page,questions[page.state.questionPosition])}>{page.state.quizSubmit ? "Submit" : "Next"}</button>
                <button disabled = {page.state.questionAnswered ? true : false} className = "questionCheck" onClick = {()=>checkQuestion(page,questions[page.state.questionPosition])}>Check</button>
        </div>
    );
    summaryBar = (
        <div className = {page.state.sideBarOpen ? "reducedQuizBar" : "fullQuizBar"}>
                <h1 className = "introText">You completed quiz : {Quiz.title}</h1>
                <h3 className = "introText"> Correct answers : {page.state.rightQuestions} / {questions.length}</h3>
                <h3 className = "introText"> You collected : {page.state.points} points </h3>
                <button className = "questionNext" onClick = {()=>returnToQuiz(page)}>return</button>
        </div>
    );
    
    return(
        
        (page.state.quizStarted ? (Quiz.layout == "vertical" ? verticalQuizBar : horizontalQuizBar) : (page.state.quizSummary ? summaryBar : introBar))
    
    );
}
function returnToQuiz(page){
    page.setState({
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
    })
}
function checkQuestion(page,question){
    var num = page.state.rightQuestions;
    var pt = page.state.points;
    var answer = AnswerComponent(page,question);
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
function startQuiz(page){
    var started = page.state.quizStarted;
    var openS = page.state.sideBarOpen;
    var openF = page.state.friendsBarOpen;
    page.setState({
        sideBarOpen : !openS,
        quizStarted :!started,
        friendsBarOpen :!openF,
    });
    
}
function nextQuestion(page,question){
    var started = page.state.quizStarted;
    var pos = page.state.questionPosition;
    var answer = AnswerComponent(page,question);
    var correctQuestions = page.state.rightQuestions;
    var num = 0;
    var pt = page.state.points;
    if(!page.state.questionChecked){
        if(answer){
            num = 1;
            pt = page.state.points + question.point;
        }
    }
        if(pos < questions.length - 2){
           page.setState({
            points : pt,
            questionChecked : false,
            questionPosition : pos + 1,
            rightQuestions : correctQuestions+num,
            questionAnswered : false,
            answerType : "notanswered"
           })
        }else if(pos == questions.length - 2){
            page.setState({
             points : pt,
             questionChecked : false,
             quizSubmit : true,
             rightQuestions : correctQuestions+num,
             questionPosition : pos + 1,
             questionAnswered : false,
             answerType : "notanswered"
          })
         }else{
            page.setState({
                questionChecked : false,
                quizStarted : !started,
                quizSummary : true,
                questionPosition : 0,
                questionAnswered : false,
                answerType : "notanswered"
            })
        }
}
export default QuizBar