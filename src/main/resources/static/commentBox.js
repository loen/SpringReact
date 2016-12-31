class CommentBox extends React.Component {
    render(){
        return(
            <div className="comment-box">
                <h3>Comments</h3>
                <h4 className="comment-count">2 comments</h4>
                <div className="comment-list">
                    <Comment author="Mc Callan" body="Scottish comment yeah !" />
                    <Comment author="Bender" body="hell yeah boby"/>
                </div>
            </div>
        );
    }
}

ReactDOM.render(<CommentBox />, document.getElementById("comment-box"));