import React from 'react';

const Modal = ({ isOpen, closeModal, data }) => {
  return (
    <div className={`modal ${isOpen ? 'is-active' : ''}`}>
      <div className="modal-background" onClick={closeModal}></div>
      <div className="modal-content">
        <pre>{JSON.stringify(data, null, 2)}</pre>
      </div>
      <button className="modal-close is-large" aria-label="close" onClick={closeModal}></button>
      <button onClick={() => window.print()}>Print</button>
    </div>
  );
};

export default Modal;
