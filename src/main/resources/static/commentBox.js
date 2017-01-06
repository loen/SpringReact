class CommentBox extends React.Component {

    _getComments(){
       return this.state.comments.map((comment)=>{
            return(
                <Comment author={comment.author}
                         body={comment.body}
                         key={comment.id}
                    />
            );
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

    _addComment(author, body){
        const comment = {
            id: this.state.comments.length + 1,
            author,
            body
        };
        this.setState({comments: this.state.comments.concat([comment]) });
    }

    _fetchCommands(){
        jQuery.ajax({
            method: 'GET',
            url: '/api/comments'
        })
    }

    constructor(){
        super();

        this.state = {
            showComments: false,
            comments: []
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
                <CommentForm addComment={this._addComment.bind(this)}/>
                <h3>Comments</h3>
                <button onClick={this._handleClick.bind(this)}>{buttonText}</button>
                <h4 className="comment-count">{this._getCommentsTitle(comments.length)}</h4>
                {commentNodes}
            </div>
        );
    }
}

ReactDOM.render(<CommentBox />, document.getElementById("comment-box"));