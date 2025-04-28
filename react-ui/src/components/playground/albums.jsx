import { useSelector } from "react-redux";

function AlbumList() {

    const { albums } = useSelector(state => state.albums); //read the albums from state
    return (
        <div>
            {
                albums.map((a, index) => (
                    <p key={index}>
                        {a.title}
                    </p>
                ))
            }
        </div>
    )
}

export default AlbumList; 