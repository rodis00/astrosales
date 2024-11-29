import React, { useEffect, useState } from "react";
import ProfileService from "../../services/profile/ProfileService";
import Loader from "../loader/Loader";
import styles from "./Profile.module.css";

const Profile = ({ userId }) => {
  const profileService = new ProfileService();

  const [userProfile, setUserProfile] = useState({});
  const [isLoading, setIsLoading] = useState(true);

  const [profileForm, setProfileForm] = useState({
    firstName: "",
    lastName: "",
    phoneNumber: "",
  });

  const noDataInfo = "Brak danych";

  useEffect(() => {
    const getUserProfile = () => {
      setIsLoading(true);
      profileService
        .getUserProfile(userId)
        .then((profile) => {
          setUserProfile(profile);
          setIsLoading(false);
        })
        .catch((err) => {
          console.log(err);
          setIsLoading(false);
        });
    };

    setTimeout(getUserProfile, 300);
  }, [userId]);

  const handleChange = (e) => {
    const { id, value } = e.target;
    setProfileForm((prevState) => ({
      ...prevState,
      [id]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Form submitted");
    profileService
      .partialUpdate(userProfile.id, profileForm)
      .then((res) => {
        setUserProfile(res);
        clearProfileForm();
        alert("Profil zaktualizowany");
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const clearProfileForm = () => {
    setProfileForm({
      firstName: "",
      lastName: "",
      phoneNumber: "",
    });
  };

  if (isLoading) {
    return <Loader />;
  }

  return (
    <div className={styles.container}>
      <form onSubmit={handleSubmit}>
        <label htmlFor="firstName">Imię:</label>
        <input
          type="text"
          id="firstName"
          value={profileForm.firstName}
          onChange={handleChange}
          placeholder={userProfile.firstName || noDataInfo}
        />

        <label htmlFor="lastName">Nazwisko:</label>
        <input
          type="text"
          id="lastName"
          value={profileForm.lastName}
          onChange={handleChange}
          placeholder={userProfile.lastName || noDataInfo}
        />

        <label htmlFor="phoneNumber">Telefon:</label>
        <input
          type="tel"
          id="phoneNumber"
          value={profileForm.phoneNumber}
          onChange={handleChange}
          placeholder={userProfile.phoneNumber || noDataInfo}
        />

        <button type="submit">Zapisz</button>
      </form>
    </div>
  );
};

export default Profile;
