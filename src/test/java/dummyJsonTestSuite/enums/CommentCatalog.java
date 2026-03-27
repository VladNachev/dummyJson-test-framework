package dummyjsontestsuite.enums;

import dummyjsontestsuite.dto.CommentDTO;
import dummyjsontestsuite.dto.CommentUserDTO;

public enum CommentCatalog {
    AWESOME_THINKING(
            new CommentDTO(
                    1,
                    "This is some awesome thinking!",
                    242,
                    3,
                    new CommentUserDTO(105, "emmac", "Emma Wilson")
            )
    ),
    GROWTH_COMMENT(
            new CommentDTO(
                    15,
                    "You've shown so much growth!",
                    6,
                    2,
                    new CommentUserDTO(17, "evelyns", "Evelyn Sanchez")
            )
    );

    private final CommentDTO comment;

    CommentCatalog(CommentDTO comment) {
        this.comment = comment;
    }

    public CommentDTO getComment() {
        return comment;
    }
}
