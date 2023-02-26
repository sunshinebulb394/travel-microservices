const Modal = ({ selectedBusId, onClose, fetchData ,props}) => {
    const handleDelete = () => {
        const token = localStorage.getItem("token")
      // Delete the selected bus using the API
      fetch(`http://localhost:8081/api/bus/delete-bus/${selectedBusId}`, {
        method: 'DELETE',
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
        .then((response) => response.json())
        .then((data) => {
          console.log(data);
          // Refresh the table data after successful delete
          // by calling the parent component's fetchData function
          props.fetchData();
          onClose();
        });
    };
  
    return (
      <div className="modal">
        <div className="modal-content">
          <p>Are you sure you want to delete this bus?</p>
          <div>
            <button type="button" className="btn btn-danger" onClick={handleDelete}>
              Delete
            </button>
            <button type="button" className="btn btn-secondary" onClick={onClose}>
              Cancel
            </button>
          </div>
        </div>
      </div>
    );
  };
  