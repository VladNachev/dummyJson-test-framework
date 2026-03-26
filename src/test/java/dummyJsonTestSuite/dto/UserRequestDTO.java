package dummyjsontestsuite.dto;

public class UserRequestDTO {
    private final String firstName;
    private final String lastName;
    private final String maidenName;
    private final int age;
    private final String gender;
    private final String email;
    private final String phone;
    private final String username;
    private final String password;
    private final String birthDate;
    private final String image;
    private final String bloodGroup;
    private final double height;
    private final double weight;
    private final String eyeColor;
    private final String role;

    public UserRequestDTO(
            String firstName,
            String lastName,
            String maidenName,
            int age,
            String gender,
            String email,
            String phone,
            String username,
            String password,
            String birthDate,
            String image,
            String bloodGroup,
            double height,
            double weight,
            String eyeColor,
            String role
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.maidenName = maidenName;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.birthDate = birthDate;
        this.image = image;
        this.bloodGroup = bloodGroup;
        this.height = height;
        this.weight = weight;
        this.eyeColor = eyeColor;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMaidenName() {
        return maidenName;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getImage() {
        return image;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public String getRole() {
        return role;
    }
}
