package dummyjsontestsuite.enums;

import dummyjsontestsuite.dto.PostDTO;

public enum PostCatalog {
    HIS_MOTHER_HAD_ALWAYS_TAUGHT_HIM(
            new PostDTO(
                    1,
                    "His mother had always taught him",
                    "His mother had always taught him not to ever think of himself as better than others. He'd tried to live by this motto. He never looked down on those who were less fortunate or who had less money than him. But the stupidity of the group of people he was talking to made him change his mind.",
                    305,
                    121
            )
    ),
    THIS_IS_IMPORTANT_TO_REMEMBER(
            new PostDTO(
                    7,
                    "This is important to remember.",
                    "This is important to remember. Love isn't like pie. You don't need to divide it among all your friends and loved ones. No matter how much love you give, you can always give more. It doesn't run out, so don't try to hold back giving it as if it may one day run out. Give it freely and as much as you want.",
                    168,
                    70
            )
    );

    private final PostDTO post;

    PostCatalog(PostDTO post) {
        this.post = post;
    }

    public PostDTO getPost() {
        return post;
    }
}
