import { useEffect, useState } from "react";

function Post() {
    const [posts, setPosts] = useState([]);
    const [idBox, setIdBox] = useState(false);
    const [userIdBox, setUserIdBox] = useState(false);
    const [userIdtext, setUserIdtext] = useState(0);
    const [postsbackup, setPostsBackup] = useState([]);

    //hook 
    useEffect(() => {
        const getPosts = () => {
            //call the API to fetch all posts data 
            fetch('https://jsonplaceholder.typicode.com/posts')
                .then(response => response.json())
                .then(data => { setPosts(data); setPostsBackup(data) })
        }

        getPosts() //imp!!!!! 
    }, []);

    const filterRecords = () => {

        if (idBox === true) {

            setPosts([...posts.sort((a, b) => b.id - a.id)])
        }
        else {
            setPosts([...posts.sort((a, b) => a.id - b.id)])
        }

    }

    const filterRecordsForUserId = (userId) => {
        console.log(userId)
        if (userId !== "") {
            console.log("In userID if " + userId)
            let temp = [...posts]
            temp = temp.filter(p => p.userId == userId)
            setPosts(temp)
        }
        else {
            console.log("In userID else " + userId)
            setPosts(postsbackup)
        }

    }

    return (
        <div style={{ padding: '20px' }}>
            <h2>All Posts using GET API</h2>
            <div className="container-fluid">
                <div className="row">
                    <div className="col-lg-12">

                        <input type="checkbox" name="id" onClick={() => {
                            if (idBox === true) { setIdBox(false) } else { setIdBox(true); }
                            filterRecords();
                        }} /> ID-DESC
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="checkbox" name="id" /> USERID-ASC
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        Enter UserID: <input type="number"
                            onKeyUp={(e) => filterRecordsForUserId(e.target.value)} />

                    </div>
                </div>
                <div className="row">
                    <div className="col-lg-12">
                        {
                            posts.map((p, index) => (
                                <div key={index}>
                                    ID: {p.id} <br />
                                    USERID: {p.userId} <br />
                                    TITLE: {p.title} <br />
                                    {p.body} <br />
                                    <hr />
                                </div>
                            ))
                        }
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Post;

//jsx = html + css + javascript 

/**
 * DOM: Document Object Model : web browser 
 * 
 * <div>
 *      <p></p>
 *      <p></p>
 * </div>
 * 
 * body 
 *    div
 *       p=5655656
 *       p=565776767 
 * 
 *   React User --> React DOM  --> Browser DOM 
 *  <div>
 *      <p key={p.id}></p>
 *      <p key={89898989}></p>
 * </div>
 * 
 */