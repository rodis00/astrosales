export interface UserProfileWithId {
  id: number;
  firstName: string;
  lastName: string;
  phoneNumber: string;
}

export interface UserProfile {
  firstName?: string | null;
  lastName?: string | null;
  phoneNumber?: string | null;
}
