def is_valid_email(email):
    if "@" not in email:
        return False
    if email.endswith("@test.com"):
        return False
    return True