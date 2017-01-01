class CommentBox extends React.Component {

    _getComments(){
        const commentsList = [
            {id: 1, author:"Billy Jean", body:"It's not my love" },
            {id: 2, author:"Bender", body:"Beer baby"}
        ]

        return commentsList.map((comment) =>{
           return(<Comment author={comment.author} body={comment.body} key={comment.id} />);
        });
    }

    _getCommentsTitle(commentsCount){
        if(commentsCount === 0){
            return "No comments yet!";
        }else if(commentsCount === 1){
            return "1 comment";
        } else {
            return `${commentsCount} comments`;
        }
    }

    _handleClick(){
        this.setState({
            showComments: !this.state.showComments
        });
    }

    constructor(){
        super();

        this.state = {
            showComments: false
        };
    }

    render(){
        const comments = this._getComments();
        let commentNodes;
        let buttonText = 'Show comments';
        if(this.state.showComments){
            buttonText = 'Hide comments';
        }
        if(this.state.showComments){
            commentNodes = <div className="comment-list">{comments}</div>;
        }
        return(
            <div className="comment-box">
                <h3>Comments</h3>
                <button onClick={this._handleClick.bind(this)}>{buttonText}</button>
                <h4 className="comment-count">{this._getCommentsTitle(comments.length)}</h4>
                {commentNodes}
            </div>
        );
    }
}

ReactDOM.render(<CommentBox />, document.getElementById("comment-box"));