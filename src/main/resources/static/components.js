class StoryBox extends React.Component {

  render(){

    const now = new Date();
    const topicsList = ["HTML", "Java script", "React"];

    return (<div>
              <h3>Stories App </h3>
              <p className="lead">
                Current time: {now.toTimeString()}
              </p>
            <ul>
              {topicsList.map(topic => <li>{topic}</li>)}
            </ul>
            </div>);
  }
}

ReactDOM.render(<StoryBox />, document.getElementById('story-app'));
