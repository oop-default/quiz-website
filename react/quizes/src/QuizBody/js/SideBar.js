import React,{Component} from 'react';
import '../css/SideBar.css';

var categories = ["sad","sad","Asd"];

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

class SideBar extends Component{
  constructor(props){
      super();
      this.state = {
        PersonalTopScore : null,
        FriendTopScore : null,
        GlobalTopScore : null,
        categories : categories,
        topScores : topScores,
        friendTopScores : friendTopScores,
        previousTries : previousTries,
        categoryBar : true,
        scoreBar : true,
      }
  }

   topUsersForm(scoreType){
    let topUser;
    {scoreType==="Global top scores" ? topUser = this.state.GlobalTopScore : topUser = this.state.FriendTopScore};
    return (
      <div>
        <div className = "rankingHeader">{scoreType}</div>
    <table style = {{borderCollapse: "separate",borderSpacing: "20px 0"}}>
    <thead>
      <tr>
        <th className = "rankingTh">&#x2116;</th>
        <th className = "rankingTh">User</th>
        <th className = "rankingTh">Score</th>
      </tr>
      </thead>
      <tbody>
      {topUser === null ? null : 
      topUser.map((data) => <tr>
      <td className = "rankingPos">{data.position}</td>
      <td className = "rankingTd">{data.user}</td>
      <td className = "rankingPos">{data.score}</td>
          </tr>)
      }
    </tbody>
    </table>
    </div>
    );
  }
   PersonalTop(){
     let myTopScores = this.state.PersonalTopScore;
    return (
    <div>
      <div className = "rankingHeader">Personal' top scores</div>
    <table style = {{borderCollapse: "separate",borderSpacing: "20px 0"}}>
    <thead>
      <tr>
        <th className = "rankingPos">&#x2116;</th>
        <th className = "rankingTh">Score</th>
      </tr>
      </thead>
      <tbody>

      {myTopScores === null ? null : 
      myTopScores.map((data) => <tr>
      <td className = "rankingPos">{data.position}</td>
      <td className = "rankingPos">{data.score}</td>
          </tr>)
      }
  
    </tbody>
    </table>
    </div>
    );
  }
//----------------------------------------------------------

componentDidMount() {
  // console.log(this.props.match.params.quizId);
  // var quizId = this.props.match.params.quizId;
  this.fetchTopScoresData(1);
}

fetchGlobalTopScores(quizID){
  var url = "http://localhost:8080/ServletGlobalTopScores?quizid=" + quizID;
  console.log(url);
  fetch(url).then(response => {
      
      if (response.ok) {
          console.log("ok status");
          return response.json();
      }
      else if (response.status == 401) {
          return null;
      } else {
          return null;
      }

  })  .then(response => this.processResponse(response,"GlobalTopScore"))
      .catch(error => {
          console.log(error);  
      });
}
fetchPersonalTopScores(quizID){
  var url = "http://localhost:8080/ServletGlobalTopScores?quizid=" + quizID;
  console.log(url);
  fetch(url).then(response => {
      
      if (response.ok) {
          console.log("ok status");
          return response.json();
      }
      else if (response.status == 401) {
          return null;
      } else {
          return null;
      }

  })  .then(response => this.processResponse(response,"PersonalTopScore"))
      .catch(error => {
          console.log(error);  
      });
}
fetchFriendTopScores(quizID){
  var url = "http://localhost:8080/ServletFriendTopScores?quizid=" + quizID;
  console.log(url);
  fetch(url).then(response => {
      
      if (response.ok) {
          console.log("ok status");
          return response.json();
      }
      else if (response.status == 401) {
          return null;
      } else {
          return null;
      }

  })  .then(response => this.processResponse(response,"FriendTopScore"))
      .catch(error => {
          console.log(error);  
      });
}
fetchTopScoresData(quizID) {
  this.fetchGlobalTopScores(quizID);
  this.fetchFriendTopScores(quizID);
  this.fetchPersonalTopScores(quizID);
}
processResponse(data,type) {
  if (data != null) {
      var jsonData = JSON.stringify(data)
      console.log(jsonData);
      this.processData(jsonData,type);
  } else if (this.state.notAuthenticated) {
      console.log("not Authenticated");
  } else {
      this.props.history.push("/notfound");
  } 
  
}

processData(jsonString,type) {
  if(type === "GlobalTopScore"){
    var object = JSON.parse(jsonString);
    console.log(object);
    this.setState({ GlobalTopScore: object });
  }else if(type === "FriendTopScore"){
    var object = JSON.parse(jsonString);
    console.log(object);
    this.setState({ FriendTopScore: object });
  }else{
    var object = JSON.parse(jsonString);
    console.log(object);
    this.setState({ PersonalTopScore: object });
  }

}

render() {
  let scoreBarContent;
  let categoryBarContent;
  let descriptionBarContent;
  var globalTop = "Global top scores";
  var friendsTop = "Friends' top scores";
  categoryBarContent = (
    <div>
      <div onClick = {()=>this.setState({categoryBar : !this.state.categoryBar})} className = "scoreBar">Categories</div>
{this.state.categoryBar ? <div>{categories.map((cat,index) => <div className = "categoryComponent">{(index+1)}.<a className = "categoryStyle" href = "fasd" >{cat}</a></div>)}</div>: null}
  </div>
  );
  descriptionBarContent = (
    <div>
    <div className = "scoreBar">Description</div>
    <div>Created by me</div>
    <div>Points 100</div>
    <div>created by me</div>
</div>
  );
    scoreBarContent = (<div>
      <div onClick = {()=>this.setState({scoreBar : !this.state.scoreBar})} className = "scoreBar">Score Bar</div>
      {this.state.scoreBar ? [this.topUsersForm(globalTop)
                                ,
                              this.topUsersForm(friendsTop),
                              this.PersonalTop()
                              ] : null}
      </div>
    );
  return(<div className = "sideBar">
          {categoryBarContent}
          {/* {sideBarDrawer} */}
          {scoreBarContent}
          {descriptionBarContent}
          {this.state.scoreBar ? null : <img style = {{height : "40%",width : "80%",marginTop : "50px"}} src = {require("./clipart.png")}></img>}
        </div>);
  }
}
export default SideBar