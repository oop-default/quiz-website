import React from 'react';

export function AnswerComponent(data,pos,page){
    var answerType = data.type;
    switch(answerType){
        case "QR" : return(QuestionResponseAnswer(pos,data,page));
        case "MC" : return(MultipleChoiceAnswer(pos,data,page));
        case "PR" : return(PictureResponseAnswer(pos,data,page));
        case "FB" : return(FillInTheBlankAnswer(pos,data,page));
        default : return(QuestionResponseAnswer(pos,data,page));
    }
}
function QuestionResponseAnswer(pos,data,page){
    var answer = document.getElementById(pos+"input").value;
    if(!(answer === null) && !(answer.length === 0)){
        page.state.answers[pos] = answer;
    }
    var realAnswers = "";
    var answerSize = data.answers.length;
    var res = false;
    for(let index = 0; index < answerSize; index++){
        realAnswers += data.answers[index].answer+", ";
        if(data.answers[index].answer === answer){
            res = true;
        }
    }
    page.state.realAnswers[pos] = realAnswers;
    return(
       res
    );
}
function MultipleChoiceAnswer(pos,data,page){
    var answerSize = data.answers.length;
    let button;
    var answer = "";
    var realAnswer = "";
    for (let index = 0; index < answerSize; index++) {
        button = document.getElementById(pos+"input"+index);
        if(button === null)continue;
        if(button.checked){
            answer = data.answers[index].answer;
        }
        if(data.answers[index].correct){
            realAnswer = data.answers[index].answer;
        }
    }

    if(!(answer === null) && !(answer.length === 0)){
        page.state.answers[pos] = answer;
    }
    page.state.realAnswers[pos] = realAnswer;
    return(realAnswer === answer);
}
function PictureResponseAnswer(pos,data,page){
    var answer = document.getElementById(pos+"input").value;
    if(!(answer === null) && !(answer.length === 0)){
        page.state.answers[pos] = answer;
    }
    var realAnswers = "";
    var answerSize = data.answers.length;
    var res = false;
    for(let index = 0; index < answerSize; index++){
        realAnswers += data.answers[index].answer+", ";
        if(data.answers[index].answer === answer){
            res = true;
        }
    }
    page.state.realAnswers[pos] = realAnswers;
    return(
       res
    );
}
function FillInTheBlankAnswer(pos,data,page){
    var answer = document.getElementById(pos+"input").value;
    if(!(answer === null) && !(answer.length === 0)){
        page.state.answers[pos] = answer;
    }
    var realAnswers = "";
    var answerSize = data.answers.length;
    var res = false;
    for(let index = 0; index < answerSize; index++){
        realAnswers += data.answers[index].answer+", ";
        if(data.answers[index].answer === answer){
            res = true;
        }
    }
    page.state.realAnswers[pos] = realAnswers;
    return(
       res
    );
}

//------------------------------------------------------------------------------------
export function QuestionComponent(data,pos){
    var questionType = data.type;
    switch(questionType){
        case "QR" : return(QuestionResponse(data,pos));
        case "MC" : return(MultipleChoice(data,pos));
        case "PR" : return(PictureResponse(data,pos));
        case "FB" : return(FillInTheBlank(data,pos));
        default : return(FillInTheBlank(data,pos));
    }
}
function QuestionResponse(data,pos){
        return(
            <div id = {pos+"question"} className = "QuizContent">
            <h3 className = "questionText">{data.question}</h3>
            <div>Insert your answer here :  <textarea id = {pos+"input"} className = "QuizInput" /></div>
            </div>
        );
}
function MultipleChoice(data,pos){
    return(
        <div id = {pos+"question"} className = "QuizContent">
        <h3 className = "questionText">{data.question}</h3>
        {data.answers.map((answer,index) => <div><input id = {pos+"input"+index} type = "radio" name = {"group"+pos} className = "introtext" value = {answer.answer} />{answer.answer}</div>)}
        </div>
    );
}
function PictureResponse(data,pos){
    return(
        <div id = {pos+"question"} className = "QuizContent">
        <h3 className = "questionText">{data.question}</h3>
        <div className = "questionText"><img style = {{position : "unset"}} src = {data.image} className = "questionImage"/></div>
        <div>Insert your answer here :  <textarea id = {pos+"input"} className = "QuizInput" /></div>
        </div>
    );
}
function FillInTheBlank(data,pos){
    return(
        <div id = {pos+"question"} className = "QuizContent">
        <h3 className = "questionText">{data.question}</h3>
        <div>Insert your answer here :  <textarea id = {pos+"input"} className = "QuizInput" /></div>
        </div>
    );
}