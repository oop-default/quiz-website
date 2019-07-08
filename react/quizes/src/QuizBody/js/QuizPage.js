import React, { Component } from 'react';
import QuizBar from './QuizBar';
import SideBar from './SideBar'
import NavBar from './NavBar'
import Friends from './Friends'
import '../css/QuizPage.css';
import cookie from 'react-cookies';
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

    // console.log("quizPage");
    //     this.fetchQuizData = this.fetchQuizData.bind(this);
    //     this.processData = this.processData.bind(this);


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
//   componentDidMount() {
//     console.log(this.props.match.params.quizId);
//     var quizId = this.props.match.params.quizId;
//     this.fetchQuizData(quizId);
// }
// fetchQuizData(quizId) {
//     var token = cookie.load('jwt');
//     var bearer = 'Bearer ' + token;
//     console.log(bearer);
//     var url = "http://localhost:8080/ServletQuiz?id=" + quizId;
//     console.log(url);
//     fetch(url, {
//         headers: {
//             'Authorization': bearer
//         }
//     }).then(response => {
        
//         if (response.ok) {
//             console.log("ok status");
//             return response.json();
//         }
//         else if (response.status == 401) {
//             return null;
//         } else {
//             return null;
//         }

//     })  .then(response => this.processResponse(response))
//         .catch(error => {
//             console.log(error);  
//         });
// }


//  processResponse(data) {
//     if (data != null) {
//         var jsonData = JSON.stringify(data)
//         console.log(jsonData);
//         this.processData(jsonData);
//     } else if (this.state.notAuthenticated) {
//         console.log("not Authenticated");
//     } else {
//         this.props.history.push("/notfound");
//     } 
    
// }

// processData(jsonString) {
//     var object = JSON.parse(jsonString);
//     var newQuiz = {
//         author: object.author,
//         title: object.title,
//         description: object.description,
//         category: object.type,
//         numPoints: object.num_points,
//         dateCreated: object.dateCreated,
//         questions: object.questions
//     }
//     this.setState({ quiz: newQuiz });
//     console.log(this.state.quiz);
// }
render() {

  // if (this.state.loading === true) {
    //   return (<div>Searching..</div>);
    // } else {
        return (
          
               <body className = "bodyStyle">
                <NavBar></NavBar>
                <SideBar ></SideBar>
               {/* {SideBar(topScores,friendTopScores,previousTries,this) } */}
               {/* {QuizBar(Quiz,this) } */}
               <QuizBar Quiz = {Quiz}></QuizBar>
               <Friends></Friends>
               </body>
        )
    


// }
        // }
      }
}

export default QuizPage