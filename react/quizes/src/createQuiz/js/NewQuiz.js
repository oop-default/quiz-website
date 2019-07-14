import React, { Component } from 'react';
import '../css/NewQuiz.css'
import { withRouter } from 'react-router-dom'

//Create array of options to be added
var quizCategoryOptions = ["History", "Math", "Art", "Other"];
var questionCategoryOptionTexts = ["Question response","Fill in blank","Multiple choice","Picture response"];
var questionCategoryOptionValues = ["QR","FB","MC","PR"];

class NewQuiz extends Component {

  constructor(props) {
      super(props);
      this.state = {
        "quizCategoryOptions":quizCategoryOptions,
        "questionCategoryOptionTexts":questionCategoryOptionTexts,
        "questionCategoryOptionValues":questionCategoryOptionValues
      }
  }

  componentDidMount() {
    this.addCategoryDropdownContent()
  }

  componentDidUpdate(prevProps, prevState) {
      console.log("componentDidUpdate");
  }

  addCategoryDropdownContent() {

    //Create and append select list
    var selectList = document.getElementById("addCategory");

    //Create and append the options
    for (var i = 0; i < this.state.quizCategoryOptions.length; i++) {
        var option = document.createElement("option");
        option.setAttribute("value", this.state.quizCategoryOptions[i]);
        option.text = this.state.quizCategoryOptions[i];
        selectList.appendChild(option);
    }
  }

  getSelectedCategory(e) {
    var value = e.options[e.selectedIndex].value;
    var text = e.options[e.selectedIndex].text;
    return value;
  }

  addNewQuestion() {
    var questionDiv = document.createElement("div");
    questionDiv.className = "questionDiv";

    // create questionTitle label
    var divisorLine =   document.createElement("hr");

    // create question label
    var question = document.createElement("label");
    var bTitle = document.createTextNode("Question * : ");

    question.appendChild(bTitle);

    // create question input form
    var questionInput = document.createElement("textarea");

    questionInput.className = "questionInput";
    questionInput.placeholder = "Eneter Question";

    var smallDivisorLine =   document.createElement("hr");

    // create type selector category label
    var categoryType = document.createElement("label");
    var type = document.createTextNode("Answer Type * : ");

    categoryType.appendChild(type);

    // create question category select
    var categorySelect = document.createElement("select");

    categorySelect.className = "selectQuestionCategory";
    this.addQuestionCategoryDropdownContent(categorySelect)

    var numPointsLabel = document.createElement("label");
    var np = document.createTextNode("Number of points * : ");

    numPointsLabel.appendChild(np);

    var numPointsInput = document.createElement("input");

    numPointsInput.className = "numPointsInput";
    numPointsInput.type = "number";
    numPointsInput.min = 1;
    numPointsInput.max = 100;

    var answersDiv = document.createElement("div");

    answersDiv.className = "answersDiv";

    // crete add answer button
    var addAnswer = document.createElement("button");

    addAnswer.type = "submit";
    addAnswer.className = "addAnswer";

    var txt = document.createTextNode("Add Answer");

    addAnswer.appendChild(txt);
    addAnswer.onclick = function () {

      // this.parentElement.removeChild(this);
      // add new answer
      var answDiv = document.createElement("div");
      answDiv.className = "answDiv";

      var selectedCategory = categorySelect.options[categorySelect.selectedIndex].value;
      var answers = this.parentElement.getElementsByClassName("answersDiv")[0];

      if (selectedCategory === 'FB' && this.parentElement.getElementsByClassName("denoteBlankSpace").length === 0) {

        var denoteBlankSpace = document.createElement("label");
        var q = document.createTextNode("Denote blanck space in question as \'______\'");

        denoteBlankSpace.className = "denoteBlankSpace";
        denoteBlankSpace.appendChild(q);
        answers.appendChild(denoteBlankSpace);

      } else if (selectedCategory === 'PR' && this.parentElement.getElementsByClassName("imgLinkInp").length === 0) {

        var imgLinkLabel = document.createElement("label");
        var ill = document.createTextNode("Picture Link * : ");

        imgLinkLabel.appendChild(ill);
        answers.appendChild(imgLinkLabel);

        var imgLinkInp = document.createElement("input");

        imgLinkInp.className = "imgLinkInp";
        imgLinkInp.type = "text";
        imgLinkInp.placeholder = "Enter picture link : ";
        answers.appendChild(imgLinkInp);
      }

      var answerLabel = document.createElement("label");
      var q = document.createTextNode("Answer * : ");

      answerLabel.className = "answerLabel";
      answerLabel.appendChild(q);

      var answerInp = document.createElement("input");

      answerInp.className = "answerInp";
      answerInp.type = "text";
      answerInp.placeholder = "Enter answer";

      // <label class="container">Is correct?
      //   <input type="checkbox" checked="checked">
      //   <span class="checkmark"></span>
      // </label>

      var checkbox = document.createElement("label");

      checkbox.className = "checkBoxLabel";

      var c = document.createTextNode("Is correct? ");

      checkbox.appendChild(c);

      var checkBoxInput = document.createElement("input");

      checkBoxInput.className = "checkBoxInput";
      checkBoxInput.type = "checkbox";
      checkBoxInput.checked = "checked";

      var checkBoxSpan = document.createElement("span");

      checkBoxSpan.className = "checkmark";

      checkbox.appendChild(checkBoxInput);
      checkbox.appendChild(checkBoxSpan);

      answDiv.appendChild(answerLabel);
      answDiv.appendChild(answerInp);
      answDiv.appendChild(checkbox);

      answers.appendChild(answDiv);

      // checkQuestionsForReadOnly();
      if (answers.getElementsByClassName("answDiv").length > 0) {

          answers.parentElement.getElementsByClassName("questionInput")[0].readOnly = true;
          answers.parentElement.getElementsByClassName("selectQuestionCategory")[0].readOnly = true;
          answers.parentElement.getElementsByClassName("numPointsInput")[0].readOnly = true;

      }
    };

    var removeLastAnswer = document.createElement("button");

    removeLastAnswer.type = "submit";
    removeLastAnswer.className = "removeLastAnswer";

    var tx = document.createTextNode("Remove last answer");

    removeLastAnswer.appendChild(tx);
    removeLastAnswer.onclick = function () {

      var ansDiv = this.parentElement.getElementsByClassName("answersDiv")[0];

      if (ansDiv.getElementsByClassName("answDiv").length > 0) {
        ansDiv.removeChild(ansDiv.lastChild);
      }

      // checkQuestionsForWrite();
      var answers = this.parentElement.getElementsByClassName("answersDiv")[0];

      if (answers.getElementsByClassName("answDiv").length === 0) {

          this.parentElement.getElementsByClassName("questionInput")[0].readOnly = false;
          this.parentElement.getElementsByClassName("selectQuestionCategory")[0].readOnly = false;
          this.parentElement.getElementsByClassName("numPointsInput")[0].readOnly = false;

        }
    }

    var questionsList = document.getElementById("questions");

    // add all noes as childs of questions list
    questionDiv.appendChild(divisorLine);
    questionDiv.appendChild(question);
    questionDiv.appendChild(questionInput);
    questionDiv.appendChild(categoryType);
    questionDiv.appendChild(categorySelect);
    questionDiv.appendChild(numPointsLabel);
    questionDiv.appendChild(numPointsInput);
    questionDiv.appendChild(answersDiv);
    questionDiv.appendChild(addAnswer);
    questionDiv.appendChild(removeLastAnswer);

    questionsList.appendChild(questionDiv);

    this.checkHeadForReadOnly();
  }

  addQuestionCategoryDropdownContent(categorySelect) {
    //Create and append the options
    for (var i = 0; i < this.state.questionCategoryOptionValues.length; i++) {

        var option = document.createElement("option");
        option.setAttribute("value", this.state.questionCategoryOptionValues[i]);
        option.text = this.state.questionCategoryOptionTexts[i];
        categorySelect.appendChild(option);

    }
  }

  removeLastQuestion() {
    var questionsDiv = document.getElementById("questions");

    if (questionsDiv.getElementsByClassName("questionDiv").length > 0) {

      if (questionsDiv.getElementsByClassName("questionDiv").length === 1) {

        document.getElementById("addTitle").readOnly = false;
        document.getElementById("addDescription").readOnly = false;
        document.getElementById("addCategory").readOnly = false;

      }

      questionsDiv.removeChild(questionsDiv.lastChild);
    }
  }

  checkHeadForReadOnly() {
    var questionsDiv = document.getElementById("questions");

    if (questionsDiv.getElementsByClassName("questionDiv").length > 0) {

        document.getElementById("addTitle").readOnly = true;
        document.getElementById("addDescription").readOnly = true;
        document.getElementById("addCategory").readOnly = true;

    }
  }

  doneCreatingQuiz() {
    var data = this.getEmptyDataObject();

    // create fields for json quiz data object

    var title = document.getElementById("addTitle").value;
    var description = document.getElementById("addDescription").value;
    var categorySelect = document.getElementById("addCategory");
    var category = categorySelect.options[categorySelect.selectedIndex].text;
    var questionsDiv = document.getElementById("questions");
    var questions = questionsDiv.getElementsByClassName("questionDiv");

    data.title = title;
    data.description = description;
    data.type = category;
    data.num_points = 0;
    data.questions = [];

    for (var i = 0; i < questions.length; i++) {

      // create fields for json quiz data object
      var questionText = questions[i].getElementsByClassName("questionInput")[0].value;
      var typeSelect = questions[i].getElementsByClassName("selectQuestionCategory")[0];
      var answerType =  typeSelect.options[typeSelect.selectedIndex].value;
      var numPoints = questions[i].getElementsByClassName("numPointsInput")[0].value;
      var answersDiv = questions[i].getElementsByClassName("answersDiv")[0];
      var answers = answersDiv.getElementsByClassName("answDiv");

      var question = this.getEmptyQuestionObject();

      question.question = questionText;
      question.type = answerType;
      question.num_point = numPoints;
      question.answers = [];

      if (answerType === 'PR') {
        var imageLink = answersDiv.getElementsByClassName("imgLinkInp")[0].value
        // imgLinkInp
        question.image = imageLink;
      }

      data.num_points += parseInt(question.num_point, 10);

      for (var j = 0; j < answers.length; j++) {

        var ans = answers[j].getElementsByClassName("answerInp")[0].value;
        var checkBox = answers[j].getElementsByClassName("checkBoxLabel")[0];
        var isCorrect = checkBox.getElementsByClassName("checkBoxInput")[0].checked;

        var answerObj = {
          "answer":ans,
          "correct":isCorrect
        };

        question.answers.push(answerObj);
      }
      data.questions.push(question);
    }

    console.log(JSON.stringify(data));
    this.checkDataJson(data);
  }

  /*
    sainity checker for input of user
    not to insert incorrevt data
  */
  checkDataJson(data) {
    // check for nulls
    if (data.title === null || data.type === null || data.description === null ||
      data.num_points === null || data.questions === null) {
        this.showError();
        return;
      }

    // check for empty entries
    if (data.title.length < 1 || data.type.length < 1 || data.description.length < 1 ||
      data.num_points < 1 || data.questions.length < 1) {
        this.showError();
        return;
      }

    /// NOW start checking questions
    for (var i = 0; i < data.questions.length; i++) {
      var question = data.questions[i];

      // check for nulls
      if (question === null || question.type === null || question.question === null ||
        question.num_point === null || question.answers === null) {
          this.showError();
          return;
        }

      // check for empty entries
      if (question.type.length < 1 || question.question.length < 1 ||
        question.num_point < 1 || question.answers.length < 1) {
          this.showError();
          return;
        }

      if (question.type === 'PR') {
        if (question.image === null) {
          this.showError();
          return;
        } else if (question.image.length < 1) {
          this.showError();
          return;
        }
      }

      var numCorrectAnswers = 0;

      /// NOW start checking answers
      for (var j = 0; j < question.answers.length; j++) {

        var ans = question.answers[j];

        // check for nulls
        if (ans.answer === null || ans.correct === null) {
          this.showError();
          return;
        }

        // check for empty entries
        if (ans.answer.length < 1) {
          this.showError();
          return;
        }

        if (ans.correct === true) {
          numCorrectAnswers += 1;
        }
      }

      if (numCorrectAnswers < 1) {
        this.showError();
        return;
      }

      if (question.type === 'MC' &&  numCorrectAnswers > 1) {
        this.showMCError();
        return;
      }

    }
    this.postData(data);
  }

  postData(data) {
    fetch('http://localhost:8080/ServletNote', {
        method: 'POST', // or 'PUT'
        body: JSON.stringify(data), // data can be `string` or {object}!
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(response => {
          if (response.status !== 200 ) {
              this.showResponceError();
          } else {
              this.showSuccess();
          }
        }).catch(error => {
                this.showResponceError();
                console.log(error);
            });
  }
  // EXAMPLE OF DATA TRANSFERED TO SERVER
  // var dataExample{
  //   "title":"title",
  //   "type":"History",
  //   "description":"description",
  //   "num_point":"03",
  //   "questions":[
  //     {
  //       "question":"first qesstion",
  //       "type":"QR",
  //       "num_point":"",
  //       "answers":[
  //         {
  //           "answer":"ans11",
  //           "correct":true
  //         },
  //         {
  //           "answer":"ans12",
  //           "correct":false
  //         }
  //       ]
  //     },
  //     {
  //       "question":"second question",
  //       "type":"FB",
  //       "num_point":"3",
  //       "answers":[
  //         {
  //           "answer":"ans21",
  //           "correct":true
  //         },
  //         {
  //           "answer":"ans22",
  //           "correct":true
  //         },
  //         {
  //           "answer":"ans23",
  //           "correct":false
  //         }
  //       ]
  //     }
  //   ]
  // };

  // red error message
  showError() {
    var errorParagraph = document.getElementById("warning");
    errorParagraph.innerHTML = "Invalid input, please try again carefully ! ";
  }

  // error for multiple choice questions
  showMCError() {
    var errorParagraph = document.getElementById("warning");
    errorParagraph.innerHTML = "Multiple Choice question must have only one correct value! ";
  }

  // succes message
  showSuccess(){
    var successParagraph = document.getElementById("success");
    successParagraph.innerHTML = "Hurray, you succesfully added new Quiz, thanks you!";
    var errorParagraph = document.getElementById("warning");
    errorParagraph.innerHTML = "";
  }

  // succes for valid input but connection problems
  showResponceError() {
    var successParagraph = document.getElementById("success");
    successParagraph.innerHTML = "";
    var errorParagraph = document.getElementById("warning");
    errorParagraph.innerHTML = "Some problem occurred, please try again.";
  }

  // empty object for quiz data
  getEmptyDataObject() {
    var data = {
      "title":null,
      "type":null,
      "description":null,
      "num_points":null,
      "questions":null
    };
    return data;
  }

  // empty object for question
  getEmptyQuestionObject() {
    var question = {
      "question":null,
      "type":null,
      "num_point":null,
      "answers":null,
      "image":null
    };
    return question;
  }

  render() {
      return (
          <div className="newQuiz">
            <div className="container">
              <h1>Create New Quiz</h1>
              <p>Please fill in this form to start creating quiz.</p>
              <p id="warning"></p>
              <p id="success"></p>
              <hr></hr>

              <label htmlFor="title"><b>Title  *</b></label>
              <input id="addTitle" className="addTitle" type="text" placeholder="Enter Title" name="title" required></input>

              <label htmlFor="description"><b>Description  *</b></label>
              <textarea id="addDescription" className="addDescription" type="text" placeholder="Enter Description" name="description" ></textarea>

              <label htmlFor="category"><b>Category  *</b></label>
              <select id="addCategory" className="selectQuizCategory"></select>

              <div id="questions" className="questionsDiv"></div>
              <hr></hr>

              <button id="addQuestion" type="submit" className="addQuestion" onClick={() => this.addNewQuestion()}>Add Question</button>
              <button id="removeQuestion" type="submit" className="removeQuestion" onClick={() => this.removeLastQuestion()}>Remove last question</button>

              <hr></hr>

              <button id="doneQuiz" type="submit" className="doneQuiz" align="right" onClick={() => this.doneCreatingQuiz()}>Done</button>

            </div>
          </div>

      );
  }
}
export default withRouter(NewQuiz);
// mC only one correct answer
// link of photo DONE
