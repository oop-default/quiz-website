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
        categories : categories,
        topScores : topScores,
        friendTopScores : friendTopScores,
        previousTries : previousTries,
        categoryBar : true,
        scoreBar : true,
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
      {this.state.categoryBar ? <ul>{
      categories.map((cat) => {
        return <li><a className = "categoryStyle" href = "iasna">{cat}</a></li>
      })
    }
    </ul> : null}
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
      {this.state.scoreBar ? [this.topUsersForm(topScores,globalTop),
                              this.topUsersForm(friendTopScores,friendsTop),
                              this.PersonalTop(previousTries)] : null}
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

   topUsersForm(topScores,scoreType){
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
      {
       this.state.topScores.map((info) => {
        return <tr>
          <td className = "rankingPos">{info.pos}</td>
          <td className = "rankingTd"><a className = "rankingref" href = "https://www.facebook.com">{info.user}</a></td>
          <td className = "rankingTd">{info.score}</td>
        </tr>
      })
    }
    </tbody>
    </table>
    </div>
    );
  }
   PersonalTop(topScores){
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
      {
      this.state.topScores.map((info) => {
        return <tr>
          <td className = "rankingPos">{info.pos}</td>
          <td className = "rankingTd">{info.score}</td>
        </tr>
      })
    }
    </tbody>
    </table>
    </div>
    );
  }
//----------------------------------------------------------
}
export default SideBar